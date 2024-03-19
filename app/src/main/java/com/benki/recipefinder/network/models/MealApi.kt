package com.benki.recipefinder.network.models

import com.benki.recipefinder.network.models.details.DetailedMeal
import com.benki.recipefinder.network.models.filters.FilterByMainIngredientWrapper
import com.benki.recipefinder.network.models.lists.ListByIngredient
import com.benki.recipefinder.network.models.lists.MealCategories
import com.benki.recipefinder.network.models.random.RandomMeal
import com.benki.recipefinder.network.models.search.SearchByName
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("lookup.php")
    suspend fun getMealDetailsById(@Query("i") mealId: String): DetailedMeal

    @GET("random.php")
    suspend fun getRandomMeal(): RandomMeal

    @GET("search.php")
    suspend fun getMealsByName(@Query("s") name: String): SearchByName

    @GET("categories.php")
    suspend fun getMealsCategories(): MealCategories

    @GET("filter.php")
    suspend fun getMealsByMainIngredient(@Query("i") ingredient: String): FilterByMainIngredientWrapper
}