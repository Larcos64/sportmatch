package com.example.sportmatch.usecases.screens.login

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.sportmatch.usecases.auth.sign_in.GoogleAuthUiClient
import com.example.sportmatch.usecases.auth.sign_in.SignInViewModel
import com.example.sportmatch.usecases.navigation.AppScreens
import com.example.sportmatch.usecases.screens.register.RegisterAs
import kotlinx.coroutines.launch

@Composable
fun SecondScreen(
    navController: NavController,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    RegisterAs(
        navController,
        userData = googleAuthUiClient.getSignedInUser(),
        onSignOut = {
            lifecycleOwner.lifecycleScope.launch {
                googleAuthUiClient.signOut()
                Toast.makeText(
                    context,
                    "Signed out",
                    Toast.LENGTH_LONG
                ).show()

                navController.popBackStack()
            }
        },
        onClickAthlete = {
            lifecycleOwner.lifecycleScope.launch {
                navController.navigate(AppScreens.AthleteRegistrationScreen.route)
            }
        },
        onClickEstablishment = {
            lifecycleOwner.lifecycleScope.launch {
                navController.navigate(AppScreens.EstablishmentRegistrationScreen.route)
            }
        }
    )
}