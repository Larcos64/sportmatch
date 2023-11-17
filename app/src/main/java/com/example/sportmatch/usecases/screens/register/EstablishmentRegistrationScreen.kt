package com.example.sportmatch.usecases.screens.register

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.sportmatch.R
import com.example.sportmatch.usecases.auth.sign_in.UserData
import com.example.sportmatch.usecases.common.appBarUtil
import com.example.sportmatch.usecases.navigation.AppScreens
import com.example.sportmatch.usecases.ui.composables.CustomOutlinedTextField
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstablishmentRegistrationScreen(
    navController: NavController,
    userData: UserData?,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
//        val context = ContentProviderCompat.requireContext()
        var scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold (
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                appBarUtil.getAppBar(
                    title = stringResource(id = R.string.establishment_registration),
                    navigation = { navController.navigateUp() }
                )
            },
            content = { innerPadding ->
                Column (
                    modifier = Modifier.padding(innerPadding),
                    // modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Profile picture
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

                    // Username
                    var filledTextName by remember {
                        mutableStateOf(userData?.username.toString())
                    }
                    CustomOutlinedTextField(
                        value = filledTextName,
                        onValueChange = { filledTextName = it },
                        leadingIconImageVector = Icons.Default.Person,
                        label = stringResource(id = R.string.name),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )

                    // Email
                    var filledTextEmail by remember {
                        mutableStateOf(userData?.email.toString())
                    }
                    CustomOutlinedTextField(
                        value = filledTextEmail,
                        onValueChange = { filledTextEmail = it },
                        leadingIconImageVector = Icons.Outlined.Email,
                        label = stringResource(id = R.string.email),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                    
                    Button(
                        onClick = {
                            createUser(
                                navController,
                                userData?.userId.toString(),
                                filledTextName,
                                filledTextEmail,
                                userData?.profilePictureUrl.toString(),
                                "ESTABLISHMENT"
                            )
                        },
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(0.9f),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
                    ) 
                    {
                        Text(text = stringResource(id = R.string.register), fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
                    }
                    /*var filledTextName by remember {
                        mutableStateOf(userData?.username.toString())
                    }
                    OutlinedTextField(
                        value = filledTextName,
                        onValueChange = { filledTextName = it },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Left
                        ),
                        label = {
                            Text(text = stringResource(id = R.string.name))
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )*/
                }
            }
            /*    values ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
            ) {
                items(100) {
                    Text(
                        text = "Item$it",
                        modifier = Modifier.padding(dimensionResource(R.dimen.sixteen))
                    )
                }
            }*/
        )
    }
}

private fun createUser(navController: NavController, userId: String, name: String, email: String, profilePictureUrl: String, role: String) {
    /*val user = mutableMapOf<String, Any>()

    user["user_id"] = userId
    user["display_name"] = name*/

    val user = UserData(
        userId = userId,
        username = name,
        email = email,
        profilePictureUrl = profilePictureUrl,
        role = role
    )

    FirebaseFirestore.getInstance().collection("users")
        .add(user)
        .addOnSuccessListener {
            Log.d("Ok", "Created ${it.id}")
            navController.navigate(AppScreens.HomeScreen.route)
            /*val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra("username", it.id)
            startActivity(intent)*/
        }.addOnFailureListener {
            Log.d("Fail", "Error ${it}")
        }
}