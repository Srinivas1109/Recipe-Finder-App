package com.benki.recipefinder.presentation.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.benki.recipefinder.network.models.lists.ListByMealCategory
import com.benki.recipefinder.network.models.lists.MealCategories

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    mealCategories: MealCategories,
    selectedCategory: ListByMealCategory?,
    onClickCategory: (ListByMealCategory) -> Unit
) {
    val state = rememberLazyStaggeredGridState()
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = "What's in your fridge?", fontWeight = FontWeight.SemiBold)
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(80.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            state = state,
            horizontalItemSpacing = 4.dp
        ) {
            mealCategories.categories?.let { categories ->
                items(items = categories, key = { it.idCategory.toString() }) { category ->
                    CategoryChip(
                        category = category,
                        selected = selectedCategory != null && selectedCategory.strCategory == category.strCategory,
                        onClick = onClickCategory
                    )
                }
            }
        }
    }
}