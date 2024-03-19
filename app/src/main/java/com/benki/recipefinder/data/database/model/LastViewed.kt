package com.benki.recipefinder.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_viewed")
data class LastViewed(
    @PrimaryKey
    val mealId: String,
    val mealName: String,
    val thumbnail: String,
    val lastModified: Long = System.currentTimeMillis(),
)
