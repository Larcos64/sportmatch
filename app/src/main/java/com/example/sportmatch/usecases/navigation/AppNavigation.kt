package com.example.sportmatch.usecases.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportmatch.usecases.auth.sign_in.GoogleAuthUiClient
import com.example.sportmatch.usecases.screens.home.HomeScreen
import com.example.sportmatch.usecases.screens.login.FirstScreen
import com.example.sportmatch.usecases.auth.sign_in.SignInViewModel
import com.example.sportmatch.usecases.screens.login.SecondScreen
import com.example.sportmatch.usecases.screens.register.AthleteRegistrationScreen
import com.example.sportmatch.usecases.screens.register.EstablishmentRegistrationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    viewModel: SignInViewModel,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.FirstScreen.route,
        builder = {
            composable(route = AppScreens.FirstScreen.route) {
                FirstScreen(
                    navController,
                    viewModel,
                    googleAuthUiClient
                )
            }
            composable(route = AppScreens.SecondScreen.route) {
                SecondScreen(
                    navController,
                    googleAuthUiClient
                )
            }
            composable(route= AppScreens.AthleteRegistrationScreen.route) {
                AthleteRegistrationScreen(
                    navController,
                    userData = googleAuthUiClient.getSignedInUser()
                )
            }
            composable(route = AppScreens.EstablishmentRegistrationScreen.route) {
                EstablishmentRegistrationScreen(
                    navController,
                    userData = googleAuthUiClient.getSignedInUser()
                )
            }
            composable(route = AppScreens.HomeScreen.route) {
                HomeScreen(
                    navController,
                    userData = googleAuthUiClient.getSignedInUser()
                )
            }
        }
    )
}