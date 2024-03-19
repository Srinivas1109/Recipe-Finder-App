package com.benki.recipefinder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.benki.recipefinder.data.database.dao.LastViewedDao
import com.benki.recipefinder.data.database.dao.SavedRecipeDao
import com.benki.recipefinder.data.database.dao.SearchHistoryDao
import com.benki.recipefinder.data.database.model.LastViewed
import com.benki.recipefinder.data.database.model.Meal
import com.benki.recipefinder.data.database.model.SearchHistory

@Database(entities = [Meal::class, SearchHistory::class, LastViewed::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun savedRecipeDao(): SavedRecipeDao
    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun lastViewedDao(): LastViewedDao
}