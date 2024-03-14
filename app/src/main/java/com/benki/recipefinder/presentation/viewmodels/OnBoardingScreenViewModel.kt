package com.benki.recipefinder.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benki.recipefinder.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) :
    ViewModel() {
    private val _username: MutableStateFlow<String> = MutableStateFlow("")
    val username = _username.asStateFlow()

    fun updateUsername(name: String) {
        _username.update { name }
    }

    private fun setUserStatus() {
        viewModelScope.launch {
            userPreferencesRepository.setUserStatus(true)
        }
    }

    private fun setUsername(name: String){
        viewModelScope.launch {
            userPreferencesRepository.setUsername(name)
        }
    }

    fun onClickGetStarted(){
        setUserStatus()
        setUsername(username.value)
    }
}