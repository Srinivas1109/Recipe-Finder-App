package com.benki.recipefinder.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benki.recipefinder.data.database.model.LastViewed
import com.benki.recipefinder.data.database.model.SearchHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface LastViewedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lastViewed: LastViewed)

    @Delete
    suspend fun delete(lastViewed: LastViewed)

    @Query("SELECT * FROM last_viewed ORDER BY lastModified DESC")
    fun getLastViewed(): Flow<List<LastViewed>>
}