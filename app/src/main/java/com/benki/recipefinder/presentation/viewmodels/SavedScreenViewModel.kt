package com.benki.recipefinder.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benki.recipefinder.data.database.model.LastViewed
import com.benki.recipefinder.data.database.model.Meal
import com.benki.recipefinder.data.repository.LocalSavedRecipesRepository
import com.benki.recipefinder.data.repository.RepositoryContainer
import com.benki.recipefinder.data.repository.Response
import com.benki.recipefinder.network.models.filters.FilterByMainIngredient
import com.benki.recipefinder.network.models.lists.ListByMealCategory
import com.benki.recipefinder.network.models.lists.MealCategories
import com.benki.recipefinder.network.models.random.RandomMeal
import com.benki.recipefinder.network.models.search.SearchByName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedScreenViewModel @Inject constructor(private val repositoryContainer: RepositoryContainer) :
    ViewModel() {
    val savedRecipes = repositoryContainer.localSavedRecipesRepository.getSavedRecipes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    fun updateQuery(q: String) {
        _query.update { q }
    }

    fun deleteSaved(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryContainer.localSavedRecipesRepository.deleteRecipe(meal)
        }
    }

    fun saveToLastViewed(lastViewed: LastViewed) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryContainer.lastViewedRepository.insertLastViewed(lastViewed)
        }
    }
}