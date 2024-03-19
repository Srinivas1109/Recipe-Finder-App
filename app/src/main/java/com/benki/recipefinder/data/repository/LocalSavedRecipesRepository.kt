package com.benki.recipefinder.data.repository

import com.benki.recipefinder.data.database.model.Meal
import kotlinx.coroutines.flow.Flow

interface LocalSavedRecipesRepository {
    suspend fun insertRecipe(meal: Meal)
    suspend fun deleteRecipe(meal: Meal)
    fun getSavedRecipes(): Flow<List<Meal>>
    fun getSavedRecipesForComparison(): List<Meal>
    fun getSavedRecipeById(id: String): Flow<Meal>
}