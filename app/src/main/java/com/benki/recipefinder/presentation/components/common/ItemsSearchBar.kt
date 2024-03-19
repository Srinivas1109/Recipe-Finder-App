package com.benki.recipefinder.presentation.components.common

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsSearchBar(
    modifier: Modifier = Modifier,
    placeholderText: String = "Search here...",
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = false,
        onActiveChange = {},
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            dividerColor = MaterialTheme.colorScheme.primary,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary
            )
        ),
        placeholder = {
            Text(text = placeholderText, fontSize = 14.sp)
        },
        trailingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Close",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
    ) {

    }
}