package com.benki.recipefinder.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.benki.recipefinder.data.constants.NavConstants.HOME_SCREEN_ROUTE
import com.benki.recipefinder.data.constants.NavConstants.PROFILE_SCREEN_ROUTE
import com.benki.recipefinder.data.constants.NavConstants.SAVED_SCREEN_ROUTE
import com.benki.recipefinder.data.constants.NavConstants.SEARCH_SCREEN_ROUTE

@Composable
fun BottomAppNavigationBar(modifier: Modifier = Modifier, navController: NavController) {
    val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
    if (navBackStackEntry != null) {
        val route = navBackStackEntry!!.destination.route
        BottomAppBar(
            modifier = modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ) {
            NavigationBarItem(
                selected = route == HOME_SCREEN_ROUTE,
                onClick = { navController.navigate(HOME_SCREEN_ROUTE) },
                icon = {
                    Icon(
                        imageVector = if (route == HOME_SCREEN_ROUTE) Icons.Filled.Home else Icons.Outlined.Home,
                        contentDescription = "Home icon"
                    )
                },
                label = {
                    Text(text = "Home", fontSize = 12.sp)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.inversePrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
            NavigationBarItem(
                selected = route == SEARCH_SCREEN_ROUTE,
                onClick = { navController.navigate(SEARCH_SCREEN_ROUTE) },
                icon = {
                    Icon(
                        imageVector = if (route == SEARCH_SCREEN_ROUTE) Icons.Filled.Search else Icons.Outlined.Search,
                        contentDescription = "Search icon"
                    )
                },
                label = {
                    Text(text = "Search", fontSize = 12.sp)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.inversePrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
            NavigationBarItem(
                selected = route == SAVED_SCREEN_ROUTE,
                onClick = { navController.navigate(SAVED_SCREEN_ROUTE) },
                icon = {
                    Icon(
                        imageVector = if (route == SAVED_SCREEN_ROUTE) Icons.Filled.Bookmark else Icons.Outlined.Bookmark,
                        contentDescription = "Saved icon"
                    )
                },
                label = {
                    Text(text = "Saved", fontSize = 12.sp)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.inversePrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
            NavigationBarItem(
                selected = route == PROFILE_SCREEN_ROUTE,
                onClick = { navController.navigate(PROFILE_SCREEN_ROUTE) },
                icon = {
                    Icon(
                        imageVector = if (route == PROFILE_SCREEN_ROUTE) Icons.Filled.Person else Icons.Outlined.Person,
                        contentDescription = "Profile icon"
                    )
                },
                label = {
                    Text(text = "Profile", fontSize = 12.sp)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.inversePrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
        }
    }
}