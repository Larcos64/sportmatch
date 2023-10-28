package com.example.sportmatch.usecases.auth

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.core.app.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportmatch.usecases.auth.sign_in.GoogleAuthUiClient
import com.example.sportmatch.usecases.auth.sign_in.SignInScreen
import com.example.sportmatch.usecases.auth.sign_in.SignInViewModel
import com.example.sportmatch.usecases.register.AthleteRegistrationScreen
import com.example.sportmatch.usecases.ui.theme.SportmatchTheme
import kotlinx.coroutines.launch
import com.google.android.gms.auth.api.identity.Identity

class MainActivity: androidx.activity.ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportmatchTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in" ) {
                        composable("sign_in") {
                            val viewModel by viewModels<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            // If the user had already joined, redirect
                            LaunchedEffect(key1 = Unit) {
                                if (googleAuthUiClient.getSignedInUser() != null) {
                                    navController.navigate("register_as")
                                }
                            }
                            
                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = {result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSinInSuccessful) {
                                if (state.isSinInSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Sin in successful",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate("register_as")
                                    viewModel.resetState()
                                }
                            }
                            
                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender?: return@launch
                                            ).build()
                                        )
                                    }
                                }
                            )
                        }
                        composable("register_as") {
                            RegisterAs(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Signed out",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.popBackStack()
                                    }
                                },
                                onClickImage = {
                                    lifecycleScope.launch {
                                        navController.navigate("athleteRegistration")
                                    }
                                }
                            )
                        }
                        composable("athleteRegistration") {
                            AthleteRegistrationScreen(
                                userData = googleAuthUiClient.getSignedInUser()
                            )
                        }
                    }
                }
            }
        }
    }
}