package com.benki.recipefinder.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benki.recipefinder.data.database.model.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedRecipeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertRecipe(meal: Meal)

    @Delete
    suspend fun deleteRecipe(meal: Meal)

    @Query("SELECT * FROM meals ORDER BY lastModified DESC")
    fun getSavedRecipes(): Flow<List<Meal>>

    @Query("SELECT * FROM meals ORDER BY lastModified DESC")
    fun getSavedRecipesForComparison(): List<Meal>

    @Query("SELECT * FROM meals WHERE idMeal= :id")
    fun getSavedRecipeById(id: String): Flow<Meal>


}