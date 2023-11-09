package com.example.sportmatch.usecases.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.sportmatch.R
import com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior

class AppBarUtil {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun getAppBar(title: String, navigation: () -> Unit) {
        var scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        return CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.tertiary,
            ),
            title = { Text(title) },
            navigationIcon = { IconButton(onClick = navigation) { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(R.string.go_back)) } },
            scrollBehavior = scrollBehavior
        )
    }
}

val appBarUtil = AppBarUtil()