package com.benki.recipefinder.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benki.recipefinder.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) :
    ViewModel() {

    private val _updatedUserName = MutableStateFlow("")
    val updatedUserName = _updatedUserName.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferencesRepository.username.collectLatest { username ->
                _updatedUserName.update { username }
            }
        }
    }

    fun saveUsername() {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferencesRepository.setUsername(_updatedUserName.value)
        }
    }

    fun updateUsername(name: String){
        _updatedUserName.update { name }
    }
}