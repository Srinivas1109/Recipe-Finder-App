package com.benki.recipefinder.data.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val newUser : Flow<Boolean>
    val username : Flow<String>
    suspend fun setUserStatus(status: Boolean)
    suspend fun setUsername(username: String)
}