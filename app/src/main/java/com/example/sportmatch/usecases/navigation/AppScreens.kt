package com.example.sportmatch.usecases.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen: AppScreens("home_screen")
}
