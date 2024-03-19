package com.benki.recipefinder.data.repository

import com.benki.recipefinder.data.database.model.SearchHistory
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {
    suspend fun insertHistory(searchHistory: SearchHistory)
    suspend fun deleteHistory(searchHistory: SearchHistory)

    fun getSearchHistory(): Flow<List<SearchHistory>>
}