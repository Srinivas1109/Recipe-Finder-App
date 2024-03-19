package com.benki.recipefinder.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey
    val idMeal: String,
    val strMeal: String? = null,
    val strMealThumb: String? = null,
    val lastModified: Long = System.currentTimeMillis(),
    val saved: Boolean = false
)
