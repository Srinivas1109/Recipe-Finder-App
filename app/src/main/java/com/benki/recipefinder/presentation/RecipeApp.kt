package com.benki.recipefinder.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.benki.recipefinder.data.constants.NavConstants
import com.benki.recipefinder.data.constants.NavConstants.DETAILED_SCREEN_ROUTE
import com.benki.recipefinder.navigation.BottomAppNavigationBar
import com.benki.recipefinder.navigation.SetupNavGraph
import com.benki.recipefinder.presentation.onboarding.OnBoardingContainer

@Composable
fun RecipeApp(startDestination: String) {
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
        val route = navBackStackEntry?.destination?.route
        if (route != null && !route.contains(DETAILED_SCREEN_ROUTE)) {
            BottomAppNavigationBar(navController = navController)
        }
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            SetupNavGraph(navController = navController, startDestination = startDestination)
        }
    }
}