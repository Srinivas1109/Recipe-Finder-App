package com.benki.recipefinder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.benki.recipefinder.data.database.dao.SavedRecipeDao
import com.benki.recipefinder.data.database.model.Meal

@Database(entities = [Meal::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun savedRecipeDao(): SavedRecipeDao
}