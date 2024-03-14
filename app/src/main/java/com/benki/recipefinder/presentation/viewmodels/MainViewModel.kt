package com.benki.recipefinder.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benki.recipefinder.data.constants.NavConstants.HOME_SCREEN_ROUTE
import com.benki.recipefinder.data.constants.NavConstants.ON_BOARDING_ROUTE
import com.benki.recipefinder.data.repository.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(userPreferencesRepository: UserPreferencesRepository) :
    ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    private val _startDestination = MutableStateFlow(ON_BOARDING_ROUTE)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.update { true }
            userPreferencesRepository.newUser.collect { onBoardingCompleted ->
                val destination = if (onBoardingCompleted) HOME_SCREEN_ROUTE else ON_BOARDING_ROUTE
                _startDestination.update {
                    destination
                }
                println("TESTING_LOG: Loading false inside")
                _loading.update { false }
            }
        }
    }

}