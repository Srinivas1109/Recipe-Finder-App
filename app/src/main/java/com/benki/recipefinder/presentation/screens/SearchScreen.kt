package com.benki.recipefinder.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.benki.recipefinder.data.database.model.LastViewed
import com.benki.recipefinder.data.database.model.SearchHistory
import com.benki.recipefinder.data.database.model.toLastViewed
import com.benki.recipefinder.presentation.components.home.RecipeSearchBar
import com.benki.recipefinder.presentation.viewmodels.SearchScreenViewModel

@Composable
fun SearchHistoryItem(
    modifier: Modifier = Modifier,
    searchHistory: SearchHistory,
    addToLastViewed: (LastViewed) -> Unit,
    navigateToDetail: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                addToLastViewed(searchHistory.toLastViewed())
                navigateToDetail(searchHistory.mealId)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                model = "${searchHistory.thumbnail}/preview",
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
            Column(modifier = modifier.padding(vertical = 4.dp)) {
                Text(text = searchHistory.mealName, fontWeight = FontWeight.Bold)
                Text(text = searchHistory.area, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun LastViewedItem(
    modifier: Modifier = Modifier,
    lastViewed: LastViewed,
    navigateToDetail: (String) -> Unit
) {
    Card(
        modifier = modifier
            .width(146.dp)
            .height(175.dp)
            .padding(vertical = 8.dp)
            .clickable { navigateToDetail(lastViewed.mealId) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = modifier
                .width(146.dp)
                .height(182.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "${lastViewed.thumbnail}/preview",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = lastViewed.mealName,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(vertical = 4.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchScreenViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
) {
    val searchHistory by viewModel.searchHistory.collectAsStateWithLifecycle()
    val lastViewed by viewModel.lastViewed.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            RecipeSearchBar(
                query = uiState.searchQuery,
                onQueryChange = viewModel::onQueryChange,
                active = uiState.searchActive,
                onActiveChange = viewModel::onActiveChange,
                recipes = uiState.recipes,
                modifier = modifier
                    .fillMaxWidth(),
                navigateToDetail = navigateToDetail,
                addToLastViewed = viewModel::saveToLastViewed
            ) { searchQuery ->
                viewModel.onSearch(searchQuery)
            }
            Spacer(modifier = modifier.height(24.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Search history", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "See All",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier.clickable { }
                )
            }

            if (searchHistory.size > 2) {
                SearchHistoryItem(
                    searchHistory = searchHistory[0],
                    navigateToDetail = navigateToDetail,
                    addToLastViewed = viewModel::saveToLastViewed
                )
                SearchHistoryItem(
                    searchHistory = searchHistory[1],
                    navigateToDetail = navigateToDetail,
                    addToLastViewed = viewModel::saveToLastViewed
                )
                SearchHistoryItem(
                    searchHistory = searchHistory[2],
                    navigateToDetail = navigateToDetail,
                    addToLastViewed = viewModel::saveToLastViewed
                )
            } else if (searchHistory.size > 1) {
                SearchHistoryItem(
                    searchHistory = searchHistory[0],
                    navigateToDetail = navigateToDetail,
                    addToLastViewed = viewModel::saveToLastViewed
                )
                SearchHistoryItem(
                    searchHistory = searchHistory[1],
                    navigateToDetail = navigateToDetail,
                    addToLastViewed = viewModel::saveToLastViewed
                )
            } else if (searchHistory.isNotEmpty()) {
                SearchHistoryItem(
                    searchHistory = searchHistory[0],
                    navigateToDetail = navigateToDetail,
                    addToLastViewed = viewModel::saveToLastViewed
                )
            } else {
                Text(
                    text = "No history found!!",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = modifier.height(24.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Last Viewed", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "See All",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier.clickable { }
                )
            }
            if (lastViewed.isNotEmpty()) {
                LazyRow(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items = lastViewed) {
                        LastViewedItem(lastViewed = it, navigateToDetail = navigateToDetail)
                    }
                }
            } else {
                Text(
                    text = "No Last Viewed recipes found!!",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}