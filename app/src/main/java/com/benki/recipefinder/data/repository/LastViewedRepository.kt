package com.benki.recipefinder.data.repository

import com.benki.recipefinder.data.database.model.LastViewed
import com.benki.recipefinder.data.database.model.SearchHistory
import kotlinx.coroutines.flow.Flow

interface LastViewedRepository {
    suspend fun insertLastViewed(lastViewed: LastViewed)
    suspend fun deleteLastViewed(lastViewed: LastViewed)
    fun getLastViewed(): Flow<List<LastViewed>>
}