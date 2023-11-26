package com.example.sportmatch.usecases.ui.composables

import android.net.http.UploadDataProvider
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.sportmatch.usecases.screens.home.HomeMenu.MenuItem
import com.example.sportmatch.usecases.screens.home.HomeMenu.MenuListItem
import com.example.sportmatch.usecases.screens.home.HomeMenu.MenuProvider

@ExperimentalMaterial3Api
@Composable
fun ComposableLargeTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.largeTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.tertiary,
                ),
                title = title,
                navigationIcon = navigationIcon,
                actions = actions,
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            val menuItems = remember {
                MenuProvider.menuList
            }
            LazyColumn(
                //contentPadding = innerPadding,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                //verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
//                val list = (0..75).map { it.toString() }
//                items(count = list.size) {
//                    Text(
//                        text = list[it],
//                        style = MaterialTheme.typography.bodyLarge,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp)
//                    )
//                }
                items(
                    menuItems,
                    itemContent = {
                        MenuListItem(menu = it)
                    }
                )
            }
        }
    )
}
