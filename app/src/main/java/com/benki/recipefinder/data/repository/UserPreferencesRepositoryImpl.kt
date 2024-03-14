package com.benki.recipefinder.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepositoryImpl(private val dataStore: DataStore<Preferences>) :
    UserPreferencesRepository {

    override val newUser: Flow<Boolean>
        get() = dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[NEW_USER] ?: false
        }

    override val username: Flow<String>
        get() = dataStore.data.map { preferences ->
            preferences[USERNAME] ?: ""
        }

    override suspend fun setUserStatus(status: Boolean) {
        dataStore.edit { preferences ->
            preferences[NEW_USER] = status
        }
    }

    override suspend fun setUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    companion object {
        private val NEW_USER = booleanPreferencesKey("new_user")
        private val USERNAME = stringPreferencesKey("username")
    }
}