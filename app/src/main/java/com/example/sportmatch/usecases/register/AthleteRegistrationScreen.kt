package com.example.sportmatch.usecases.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.sportmatch.R
import com.example.sportmatch.usecases.auth.sign_in.UserData

@Composable
fun AthleteRegistrationScreen(
    userData: UserData?,
) {
    // Contenido de la pantalla de registro de atletas
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var filledTextName by remember {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = userData?.username.toString(),
                onValueChange = { filledTextName = it },
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Right
                ),
                label = {
                    Text(text = stringResource(id = R.string.name))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thirty)))

            var filledTextEmail by remember {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = filledTextEmail,
                onValueChange = { filledTextEmail = it },
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Right
                ),
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Email, contentDescription = stringResource(id = R.string.email))
                }
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thirty)))

            var filledTextPass by remember {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = filledTextPass,
                onValueChange = { filledTextPass = it },
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Right
                ),
                label = {
                    Text(text = stringResource(id = R.string.password))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Lock, contentDescription = stringResource(id = R.string.password))
                }
            )
        }
    }
}