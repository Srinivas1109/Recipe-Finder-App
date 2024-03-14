package com.benki.recipefinder.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benki.recipefinder.data.repository.UserPreferencesRepository
import com.benki.recipefinder.network.models.MealApi
import com.benki.recipefinder.network.models.filters.FilterByMainIngredient
import com.benki.recipefinder.network.models.filters.FilterByMainIngredientWrapper
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeScreenUiState(
    val randomMeal: RandomMeal = RandomMeal(),
    val searchQuery: String = "",
    val hasNewNotifications: Boolean = false,
    val username: String = "",
    val searchActive: Boolean = false,
    val recipes: SearchByName = SearchByName(),
    val mealCategories: MealCategories = MealCategories(),
    val selectedByMealCategory: ListByMealCategory? = null,
    val mealsByMainIngredient: FilterByMainIngredientWrapper = FilterByMainIngredientWrapper()
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val mealApi: MealApi,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<HomeScreenUiState> =
        MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    init {
        getUsername()
        getRandomMeal()
        getMealCategories()
    }

    private fun getRandomMeal() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                viewModelScope.launch {
                    val randomMeal = mealApi.getRandomMeal()
                    _uiState.update {
                        it.copy(randomMeal = randomMeal)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getUsername() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                viewModelScope.launch {
                    userPreferencesRepository.username.collectLatest { username ->
                        _uiState.update {
                            it.copy(username = username)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getMealCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                viewModelScope.launch {
                    val mealCategories = mealApi.getMealsCategories()
                    _uiState.update {
                        it.copy(
                            mealCategories = mealCategories,
                            selectedByMealCategory = if (!mealCategories.categories.isNullOrEmpty()) mealCategories.categories.first() else null
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMealByMainIngredient() {
        uiState.value.selectedByMealCategory?.let { selectedCategory ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    selectedCategory.strCategory?.let { selectedCategoryName ->
                        viewModelScope.launch {
                            val mealsByMainIngredient =
                                mealApi.getMealsByMainIngredient(selectedCategoryName)
                            _uiState.update {
                                it.copy(mealsByMainIngredient = mealsByMainIngredient)
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun updateSelectedCategory(category: ListByMealCategory) {
        _uiState.update {
            it.copy(selectedByMealCategory = category)
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
                val recipes = mealApi.getMealsByName(query)
                _uiState.update {
                    it.copy(recipes = recipes)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}