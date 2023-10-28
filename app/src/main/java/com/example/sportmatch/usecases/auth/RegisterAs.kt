package com.example.sportmatch.usecases.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.sportmatch.R
import com.example.sportmatch.usecases.auth.sign_in.UserData

@Composable
fun RegisterAs(
    userData: UserData?,
    onSignOut: () -> Unit,
    onClickImage: () -> Unit,
) {
    val context = LocalContext.current
    val navController = rememberNavController()

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (userData?.profilePictureUrl != null) {
            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = onSignOut) {
            Text(text = "Cerrar sesión")
        }
    }
    if (userData?.username != null) {
        Text(
            text = userData.username,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.athlete))
        Spacer(Modifier.height(16.dp))
        Image(
            modifier = Modifier.clickable(
                enabled = true,
                onClickLabel = stringResource(id = R.string.athlete),
                onClick = onClickImage
            ),
            painter = painterResource(id = R.drawable.img_athletes),
            contentDescription = "Imagen de registro",
        )
    }

    // Define la ruta en tu sistema de navegación
    /*NavHost(
        navController = navController,
        startDestination = "athleteRegistration"
    ) {
        composable("athleteRegistration") {
            AthleteRegistrationScreen(
                username = userData?.username.toString()
            )
        }
        // Otras rutas y composables
    }*/
}