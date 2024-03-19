package com.benki.recipefinder.data.repository

import com.benki.recipefinder.network.models.filters.FilterByMainIngredient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryContainerImpl(
    override val localSavedRecipesRepository: LocalSavedRecipesRepository,
    override val remoteRecipesRepository: RemoteRecipesRepository,
    override val searchHistoryRepository: SearchHistoryRepository,
    override val lastViewedRepository: LastViewedRepository
) : RepositoryContainer {
    override suspend fun getMealsByMainIngredient(ingredient: String): Response<List<FilterByMainIngredient>> {
        val response = remoteRecipesRepository.getMealsByMainIngredient(ingredient)
        return if (response.isSuccess) {
            withContext(Dispatchers.IO){
                val localData = localSavedRecipesRepository.getSavedRecipesForComparison()
                val meals = response.data.map { remoteMeal ->
                    if (localData.find { localMeal -> localMeal.idMeal == remoteMeal.idMeal } != null) remoteMeal.copy(
                        saved = true
                    ) else remoteMeal
                }
                response.copy(data = meals)
            }

        } else {
            response
        }
    }

}