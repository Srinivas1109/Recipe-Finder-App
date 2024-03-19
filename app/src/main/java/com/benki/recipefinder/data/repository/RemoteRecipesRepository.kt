package com.benki.recipefinder.data.repository

import com.benki.recipefinder.network.models.details.DetailedMeal
import com.benki.recipefinder.network.models.filters.FilterByMainIngredient
import com.benki.recipefinder.network.models.filters.FilterByMainIngredientWrapper
import com.benki.recipefinder.network.models.lists.MealCategories
import com.benki.recipefinder.network.models.random.RandomMeal
import com.benki.recipefinder.network.models.search.SearchByName

interface RemoteRecipesRepository {
    suspend fun getRandomMeal(): Response<RandomMeal>
    suspend fun getMealById(id: String): Response<DetailedMeal>
    suspend fun getMealByName(name: String): Response<SearchByName>
    suspend fun getMealsCategories(): Response<MealCategories>
    suspend fun getMealsByMainIngredient(ingredient: String): Response<List<FilterByMainIngredient>>
}