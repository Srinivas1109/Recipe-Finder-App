package com.benki.recipefinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.benki.recipefinder.data.constants.NavConstants.HOME_SCREEN_ROUTE
import com.benki.recipefinder.data.constants.NavConstants.ON_BOARDING_ROUTE
import com.benki.recipefinder.data.constants.NavConstants.PROFILE_SCREEN_ROUTE
import com.benki.recipefinder.data.constants.NavConstants.SAVED_SCREEN_ROUTE
import com.benki.recipefinder.data.constants.NavConstants.SEARCH_SCREEN_ROUTE
import com.benki.recipefinder.presentation.onboarding.OnBoardingContainer
import com.benki.recipefinder.presentation.screens.HomeScreen
import com.benki.recipefinder.presentation.screens.ProfileScreen
import com.benki.recipefinder.presentation.screens.SavedScreen
import com.benki.recipefinder.presentation.screens.SearchScreen
import com.benki.recipefinder.presentation.viewmodels.OnBoardingScreenViewModel

@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = ON_BOARDING_ROUTE) {
            val viewModel: OnBoardingScreenViewModel = hiltViewModel()
            val username by viewModel.username.collectAsStateWithLifecycle()
            OnBoardingContainer(username = username, updateUsername = viewModel::updateUsername) {
                viewModel.onClickGetStarted()
                navController.navigate(route = HOME_SCREEN_ROUTE)
            }
        }
        composable(route = HOME_SCREEN_ROUTE) {
            HomeScreen()
        }
        composable(route = SEARCH_SCREEN_ROUTE) {
            SearchScreen()
        }
        composable(route = SAVED_SCREEN_ROUTE) {
            SavedScreen()
        }
        composable(route = PROFILE_SCREEN_ROUTE) {
            ProfileScreen()
        }
    }
}