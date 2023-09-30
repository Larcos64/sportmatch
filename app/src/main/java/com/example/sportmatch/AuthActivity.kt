package com.example.sportmatch

import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException


class AuthActivity : AppCompatActivity() {
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    // private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    // private var showOneTapUI = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val btnGoogle: Button = findViewById(R.id.btnGoogleSignIn)

        oneTapClient = Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build()

        val activityResultLauncher: ActivityResultLauncher<IntentSenderRequest> = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            try {
                val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                val idToken = credential.googleIdToken
                // val username = credential.id
                val password = credential.password
                when {
                    idToken != null -> {
                        // Got an ID token from Google. Use it to authenticate
                        // with your backend.
                        val email = credential.getId()
                        Toast.makeText(applicationContext, "Email: " + email, Toast.LENGTH_LONG).show()
                    }
                    password != null -> {
                        // Got a saved username and password. Use them to authenticate
                        // with your backend.
                        Toast.makeText(applicationContext, "Got password", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        // Shouldn't happen.
                        Toast.makeText(applicationContext, "No ID token or password!", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }

        btnGoogle.setOnClickListener {
            oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(this) { result ->
                    try {
                        val intentSenderRequest = IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
                        activityResultLauncher.launch(intentSenderRequest)
                    } catch (e: IntentSender.SendIntentException) {
                        Log.e("TAG", "Couldn't start One Tap UI: ${e.localizedMessage}")
                    }
                }
                .addOnFailureListener(this) { e ->
                    // No saved credentials found. Launch the One Tap sign-up flow, or
                    // do nothing and continue presenting the signed-out UI.
                    Log.d("TAG", e.localizedMessage)
                }
        }
    }
}