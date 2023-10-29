package com.example.sportmatch.usecases.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.sportmatch.R
import com.example.sportmatch.usecases.auth.sign_in.UserData
import com.example.sportmatch.usecases.common.appBarUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstablishmentRegistrationScreen(
    userData: UserData?,
) {
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
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.tertiary,
                    ),
                    title = { Text(stringResource(id = R.string.establishment_registration)) },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            }
        ) {
                values ->
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
            }
        }
    }
}