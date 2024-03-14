package com.benki.recipefinder.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benki.recipefinder.presentation.components.home.CategoryList
import com.benki.recipefinder.presentation.components.home.Header
import com.benki.recipefinder.presentation.components.home.MealsByMainIngredientsList
import com.benki.recipefinder.presentation.components.home.RecipeSearchBar
import com.benki.recipefinder.presentation.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val name = uiState.username
    val randomMeal = uiState.randomMeal
    val hasNewNotifications = uiState.hasNewNotifications
    val query = uiState.searchQuery
    val searchActive = uiState.searchActive
    val recipes = uiState.recipes
    val mealCategories = uiState.mealCategories
    val selectedByMealCategory = uiState.selectedByMealCategory
    val mealsByMainIngredient = uiState.mealsByMainIngredient

    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primaryContainer) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (!randomMeal.meals.isNullOrEmpty()) {
                randomMeal.meals.first().strMealThumb?.let {
                    Header(
                        name = name,
                        randomImage = it,
                        hasNewNotifications = hasNewNotifications,
                        modifier = modifier.padding(vertical = 24.dp, horizontal = 16.dp)
                    )
                }
            }
            RecipeSearchBar(
                modifier = modifier.padding(horizontal = 16.dp),
                query = query,
                onQueryChange = viewModel::onQueryChange,
                active = searchActive,
                onActiveChange = viewModel::onActiveChange,
                recipes = recipes
            ) { searchQuery ->
                viewModel.onSearch(searchQuery)
            }
            Spacer(modifier = modifier.height(16.dp))
            CategoryList(
                mealCategories = mealCategories,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 0.dp),
                selectedCategory = selectedByMealCategory,
                onClickCategory = viewModel::updateSelectedCategory
            )
            MealsByMainIngredientsList(
                mealsByMainIngredient = mealsByMainIngredient,
                selectedCategory = selectedByMealCategory
            )
        }
    }

    LaunchedEffect(key1 = selectedByMealCategory) {
        viewModel.getMealByMainIngredient()
    }
}
