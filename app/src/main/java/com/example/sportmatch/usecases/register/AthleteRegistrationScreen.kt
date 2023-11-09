package com.example.sportmatch.usecases.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.sportmatch.R
import com.example.sportmatch.usecases.auth.sign_in.UserData
import com.example.sportmatch.usecases.common.BackButton
import com.example.sportmatch.usecases.common.appBarUtil
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AthleteRegistrationScreen(
    navController: NavController,
    userData: UserData?,
) {
    // Contenido de la pantalla de registro de atletas
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold (
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = { Text(stringResource(id = R.string.establishment_registration)) },
                    navigationIcon = {
                        BackButton(navController = navController, icon = Icons.Filled.ArrowBack, contentDescription = "Localized description")
                        /*IconButton(onClick = { navController }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }*/
                    }
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
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thirty)))

                    // Email
                    var filledTextEmail by remember {
                        mutableStateOf(userData?.email.toString())
                    }
                    OutlinedTextField(
                        value = filledTextEmail,
                        onValueChange = { filledTextEmail = it },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Left
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

                    // Sports profile
                    val options = listOf(
                        stringResource(id = R.string.sp_athlete),
                        stringResource(id = R.string.sp_basketball_player),
                        stringResource(id = R.string.sp_cycist),
                        stringResource(id = R.string.sp_soccer_player),
                        stringResource(id = R.string.sp_swimmer),
                        stringResource(id = R.string.sp_tri_athlete),
                    )
                    var expanded by remember { mutableStateOf(false) }
                    var selectedOptionText by remember { mutableStateOf(options[0]) }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        TextField(
                            value = selectedOptionText,
                            label = { Text(stringResource(id = R.string.sports_profile)) },
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(text = selectionOption) },
                                    onClick = {
                                        selectedOptionText = selectionOption
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    /*Button(onClick = { saveNameToFirestore() }) {
                        Text("Save")
                    }*/

                    /*Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.thirty)))

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
                    )*/
                }
            }
        )


    }
}