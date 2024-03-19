package com.benki.recipefinder.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistory(
    @PrimaryKey
    val mealId: String,
    val mealName: String,
    val area: String,
    val thumbnail: String,
    val lastModified: Long = System.currentTimeMillis(),
)

fun SearchHistory.toLastViewed(): LastViewed {
    return LastViewed(mealId = mealId, mealName = mealName, thumbnail = thumbnail)
}
