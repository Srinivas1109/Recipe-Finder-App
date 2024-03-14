package com.benki.recipefinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benki.recipefinder.data.constants.NavConstants.HOME_SCREEN_ROUTE
import com.benki.recipefinder.data.constants.NavConstants.ON_BOARDING_ROUTE
import com.benki.recipefinder.presentation.RecipeApp
import com.benki.recipefinder.presentation.viewmodels.MainViewModel
import com.benki.recipefinder.ui.theme.RecipeFinderTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            mainViewModel.loading.value
        }
        setContent {
            RecipeFinderTheme {
                val startDestination by mainViewModel.startDestination.collectAsStateWithLifecycle()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeApp(startDestination = startDestination)
                }
            }
        }
    }
}