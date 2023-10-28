package com.example.sportmatch.usecases.auth.sign_in

import java.lang.Error

data class SignInState(
    val isSinInSuccessful: Boolean = false,
    val signInError: String? = null
)
