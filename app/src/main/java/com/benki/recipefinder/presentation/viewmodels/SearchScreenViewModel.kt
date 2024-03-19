package com.benki.recipefinder.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benki.recipefinder.data.database.model.LastViewed
import com.benki.recipefinder.data.repository.RepositoryContainer
import com.benki.recipefinder.network.models.MealApi
import com.benki.recipefinder.network.models.details.Meal
import com.benki.recipefinder.network.models.details.toLastViewed
import com.benki.recipefinder.network.models.details.toSearchHistory
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

data class SearchScreenUiState(
    val searchQuery: String = "",
    val searchActive: Boolean = false,
    val recipes: SearchByName = SearchByName(),
)

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repositoryContainer: RepositoryContainer,
    private val mealApi: MealApi
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<SearchScreenUiState> =
        MutableStateFlow(SearchScreenUiState())
    val uiState: StateFlow<SearchScreenUiState> = _uiState.asStateFlow()

    val searchHistory = repositoryContainer.searchHistoryRepository.getSearchHistory()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())
    val lastViewed = repositoryContainer.lastViewedRepository.getLastViewed()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    fun saveToLastViewed(lastViewed: LastViewed) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryContainer.lastViewedRepository.insertLastViewed(lastViewed)
        }
    }

    fun onQueryChange(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }

    fun onActiveChange(active: Boolean) {
        _uiState.update {
            it.copy(searchActive = active)
        }
    }

    fun onSearch(query: String) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val recipes: SearchByName = mealApi.getMealsByName(query)
                if (!recipes.meals.isNullOrEmpty()) {
                    val meals = recipes.meals
                    meals.forEach {
                        repositoryContainer.searchHistoryRepository.insertHistory(it.toSearchHistory())
                    }
                }
                _uiState.update {
                    it.copy(recipes = recipes)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}