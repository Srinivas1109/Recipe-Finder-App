package com.benki.recipefinder.presentation.components.home

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benki.recipefinder.data.database.model.LastViewed
import com.benki.recipefinder.network.models.details.toLastViewed
import com.benki.recipefinder.network.models.search.SearchByName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    recipes: SearchByName = SearchByName(),
    onQueryChange: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    navigateToDetail: (String) -> Unit,
    addToLastViewed: (LastViewed) -> Unit,
    onSearch: (String) -> Unit,
) {
    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .then(if (active) Modifier.height(250.dp) else Modifier.height(53.dp)),
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        shape = RoundedCornerShape(8.dp),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            dividerColor = MaterialTheme.colorScheme.primary,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary
            )
        ),
        placeholder = {
            Text(text = "Search recipes...", fontSize = 14.sp)
        },
        trailingIcon = {
            IconButton(onClick = { if (active) onActiveChange(false) else onActiveChange(true) }) {
                Icon(
                    imageVector = if (active) Icons.Outlined.Close else Icons.Outlined.Search,
                    contentDescription = "Close",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
    ) {
        if (recipes.meals == null) {
            Text(
                text = "No recipes found!!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(items = recipes.meals, key = { it.idMeal.toString() }) {
                    SearchItem(
                        meal = it,
                        modifier = modifier.padding(horizontal = 0.dp, vertical = 8.dp)
                    ) { meal ->
                        addToLastViewed(meal.toLastViewed())
                        navigateToDetail(meal.idMeal!!)
                    }
                }

            }
        }

    }
}