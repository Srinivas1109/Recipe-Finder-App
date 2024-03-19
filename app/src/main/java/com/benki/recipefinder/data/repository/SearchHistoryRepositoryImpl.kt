package com.benki.recipefinder.data.repository

import com.benki.recipefinder.data.database.dao.SearchHistoryDao
import com.benki.recipefinder.data.database.model.SearchHistory
import kotlinx.coroutines.flow.Flow

class SearchHistoryRepositoryImpl(private val searchHistoryDao: SearchHistoryDao) :
    SearchHistoryRepository {
    override suspend fun insertHistory(searchHistory: SearchHistory) {
        searchHistoryDao.insert(searchHistory)
    }

    override suspend fun deleteHistory(searchHistory: SearchHistory) {
        searchHistoryDao.delete(searchHistory)
    }

    override fun getSearchHistory(): Flow<List<SearchHistory>> {
        return searchHistoryDao.getSearchHistory()
    }
}