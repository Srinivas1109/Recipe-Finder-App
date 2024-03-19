package com.benki.recipefinder.data.repository

import com.benki.recipefinder.data.database.dao.SavedRecipeDao
import com.benki.recipefinder.data.database.model.Meal
import kotlinx.coroutines.flow.Flow

class LocalSavedRecipesRepositoryImpl(private val savedRecipeDao: SavedRecipeDao) :
    LocalSavedRecipesRepository {
    override suspend fun insertRecipe(meal: Meal) {
        try {
            savedRecipeDao.insertRecipe(meal)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteRecipe(meal: Meal) {
        try {
            savedRecipeDao.deleteRecipe(meal)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getSavedRecipes(): Flow<List<Meal>> {
        return savedRecipeDao.getSavedRecipes()
    }

    override fun getSavedRecipesForComparison(): List<Meal> {
        return savedRecipeDao.getSavedRecipesForComparison()
    }

    override fun getSavedRecipeById(id: String): Flow<Meal> {
        return savedRecipeDao.getSavedRecipeById(id)
    }
}