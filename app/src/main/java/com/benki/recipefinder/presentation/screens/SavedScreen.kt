package com.benki.recipefinder.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benki.recipefinder.presentation.components.common.SavedRecipeItem
import com.benki.recipefinder.presentation.viewmodels.SavedScreenViewModel

@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    viewModel: SavedScreenViewModel = hiltViewModel(),
    navigateToDetails: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val savedRecipes by viewModel.savedRecipes.collectAsStateWithLifecycle()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            Surface(
                modifier = modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                    Text(
                        text = "Saved Recipes",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (savedRecipes.isNotEmpty()) {
                    LazyColumn(modifier = modifier.fillMaxSize()) {
                        items(items = savedRecipes) {
                            SavedRecipeItem(
                                meal = it,
                                deleteSaved = viewModel::deleteSaved,
                                navigateToDetails = navigateToDetails
                            )
                        }
                    }
                } else {
                    Text(text = "You have no saved recipes")
                }

            }
        }
    }
}