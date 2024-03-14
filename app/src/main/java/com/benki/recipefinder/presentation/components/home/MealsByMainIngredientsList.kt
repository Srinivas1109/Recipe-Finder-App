package com.benki.recipefinder.presentation.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benki.recipefinder.network.models.filters.FilterByMainIngredientWrapper
import com.benki.recipefinder.network.models.lists.ListByMealCategory

@Composable
fun MealsByMainIngredientsList(
    modifier: Modifier = Modifier,
    mealsByMainIngredient: FilterByMainIngredientWrapper,
    selectedCategory: ListByMealCategory?
) {
    val state = rememberLazyListState()
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        selectedCategory?.strCategory?.let { categoryName ->
            Text(
                text = "Recipes with $categoryName",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(16.dp)
            )
        }
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp), state = state
        ) {
            mealsByMainIngredient.meals?.let { meals ->
                items(items = meals) { meal ->
                    MealsByMainIngredientsItem(mealByMainIngredient = meal)
                }
            }
        }
    }
}