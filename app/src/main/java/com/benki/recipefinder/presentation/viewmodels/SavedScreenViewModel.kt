package com.benki.recipefinder.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benki.recipefinder.data.database.model.Meal
import com.benki.recipefinder.data.repository.LocalSavedRecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedScreenViewModel @Inject constructor(private val localSavedRecipesRepository: LocalSavedRecipesRepository) :
    ViewModel() {
    val savedRecipes = localSavedRecipesRepository.getSavedRecipes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    fun deleteSaved(meal: Meal){
        viewModelScope.launch(Dispatchers.IO) {
            localSavedRecipesRepository.deleteRecipe(meal)
        }
    }
}