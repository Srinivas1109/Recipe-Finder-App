package com.benki.recipefinder.data.repository

import com.benki.recipefinder.data.database.dao.LastViewedDao
import com.benki.recipefinder.data.database.dao.SearchHistoryDao
import com.benki.recipefinder.data.database.model.LastViewed
import com.benki.recipefinder.data.database.model.SearchHistory
import kotlinx.coroutines.flow.Flow

class LastViewedRepositoryImpl(private val lastViewedDao: LastViewedDao) :
    LastViewedRepository {
    override suspend fun insertLastViewed(lastViewed: LastViewed) {
        lastViewedDao.insert(lastViewed)
    }

    override suspend fun deleteLastViewed(lastViewed: LastViewed) {
        lastViewedDao.delete(lastViewed)
    }

    override fun getLastViewed(): Flow<List<LastViewed>> {
        return lastViewedDao.getLastViewed()
    }

}