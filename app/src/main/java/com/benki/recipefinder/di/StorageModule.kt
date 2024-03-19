package com.benki.recipefinder.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.benki.recipefinder.data.constants.StorageConstants
import com.benki.recipefinder.data.database.RecipeDatabase
import com.benki.recipefinder.data.database.dao.LastViewedDao
import com.benki.recipefinder.data.database.dao.SavedRecipeDao
import com.benki.recipefinder.data.database.dao.SearchHistoryDao
import com.benki.recipefinder.data.repository.LastViewedRepository
import com.benki.recipefinder.data.repository.LastViewedRepositoryImpl
import com.benki.recipefinder.data.repository.LocalSavedRecipesRepository
import com.benki.recipefinder.data.repository.LocalSavedRecipesRepositoryImpl
import com.benki.recipefinder.data.repository.RemoteRecipesRepository
import com.benki.recipefinder.data.repository.RemoteRecipesRepositoryImpl
import com.benki.recipefinder.data.repository.RepositoryContainer
import com.benki.recipefinder.data.repository.RepositoryContainerImpl
import com.benki.recipefinder.data.repository.SearchHistoryRepository
import com.benki.recipefinder.data.repository.SearchHistoryRepositoryImpl
import com.benki.recipefinder.data.repository.UserPreferencesRepository
import com.benki.recipefinder.data.repository.UserPreferencesRepositoryImpl
import com.benki.recipefinder.network.models.MealApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.datastore by preferencesDataStore(StorageConstants.USER_PREFERENCES_NAME)


@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    @Provides
    @Singleton
    fun provideDatastore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.datastore
    }

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(dataStore: DataStore<Preferences>): UserPreferencesRepository {
        return UserPreferencesRepositoryImpl(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): RecipeDatabase {
        return Room.databaseBuilder(
            appContext,
            RecipeDatabase::class.java,
            "RecipeDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSavedRecipeDao(recipeDatabase: RecipeDatabase): SavedRecipeDao {
        return recipeDatabase.savedRecipeDao()
    }

    @Provides
    @Singleton
    fun provideSearchHistoryDao(recipeDatabase: RecipeDatabase): SearchHistoryDao {
        return recipeDatabase.searchHistoryDao()
    }

    @Provides
    @Singleton
    fun provideLastViewedDao(recipeDatabase: RecipeDatabase): LastViewedDao {
        return recipeDatabase.lastViewedDao()
    }

    @Provides
    @Singleton
    fun provideRemoteRecipeRepository(mealApi: MealApi): RemoteRecipesRepository =
        RemoteRecipesRepositoryImpl(mealApi = mealApi)

    @Provides
    @Singleton
    fun provideLocalSavedRecipeRepository(savedRecipeDao: SavedRecipeDao): LocalSavedRecipesRepository =
        LocalSavedRecipesRepositoryImpl(savedRecipeDao = savedRecipeDao)

    @Provides
    @Singleton
    fun provideSearchHistoryRepository(searchHistoryDao: SearchHistoryDao): SearchHistoryRepository =
        SearchHistoryRepositoryImpl(searchHistoryDao = searchHistoryDao)

    @Provides
    @Singleton
    fun provideLastViewedRepository(lastViewedDao: LastViewedDao): LastViewedRepository =
        LastViewedRepositoryImpl(lastViewedDao = lastViewedDao)

    @Provides
    @Singleton
    fun provideRepositoryContainer(
        localSavedRecipesRepository: LocalSavedRecipesRepository,
        remoteRecipesRepository: RemoteRecipesRepository,
        searchHistoryRepository: SearchHistoryRepository,
        lastViewedRepository: LastViewedRepository,
    ): RepositoryContainer =
        RepositoryContainerImpl(
            localSavedRecipesRepository = localSavedRecipesRepository,
            remoteRecipesRepository = remoteRecipesRepository,
            searchHistoryRepository = searchHistoryRepository,
            lastViewedRepository = lastViewedRepository
        )
}