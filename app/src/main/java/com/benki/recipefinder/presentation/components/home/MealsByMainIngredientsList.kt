package com.benki.recipefinder.presentation.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benki.recipefinder.data.database.model.LastViewed
import com.benki.recipefinder.data.repository.Response
import com.benki.recipefinder.network.models.filters.FilterByMainIngredient
import com.benki.recipefinder.network.models.filters.FilterByMainIngredientWrapper
import com.benki.recipefinder.network.models.lists.ListByMealCategory

@Composable
fun MealsByMainIngredientsList(
    modifier: Modifier = Modifier,
    mealsByMainIngredient: Response<List<FilterByMainIngredient>>,
    selectedCategory: ListByMealCategory?,
    navigateToDetails: (String) -> Unit,
    addToLastViewed: (LastViewed) -> Unit,
    addToSaved: (FilterByMainIngredient) -> Unit,
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
        if (mealsByMainIngredient.isSuccess) {
            LazyRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp), state = state
            ) {
                items(items = mealsByMainIngredient.data) { meal ->
                    MealsByMainIngredientsItem(mealByMainIngredient = meal, addToSaved = addToSaved, navigateToDetails = navigateToDetails, addToLastViewed = addToLastViewed)
                }
            }
        } else {
            mealsByMainIngredient.errorMessage?.let {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }

            }
        }

    }
}