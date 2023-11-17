package com.example.sportmatch.usecases.navigation

sealed class AppScreens(val route: String) {
    object FirstScreen: AppScreens("first_screen")
    object SecondScreen: AppScreens("register_as")
    object AthleteRegistrationScreen: AppScreens("athlete_registration")
    object EstablishmentRegistrationScreen: AppScreens("establishment_registration")
    object HomeScreen: AppScreens("home_screen")
}
