package com.benki.recipefinder.data.repository

import com.benki.recipefinder.network.models.filters.FilterByMainIngredient
import com.benki.recipefinder.network.models.filters.FilterByMainIngredientWrapper

interface RepositoryContainer {
    val localSavedRecipesRepository: LocalSavedRecipesRepository
    val remoteRecipesRepository: RemoteRecipesRepository
    val searchHistoryRepository: SearchHistoryRepository
    val lastViewedRepository: LastViewedRepository

    suspend fun getMealsByMainIngredient(ingredient: String): Response<List<FilterByMainIngredient>>

}