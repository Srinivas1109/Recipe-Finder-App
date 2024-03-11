package com.benki.recipefinder.network.models

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("lookup.php")
    suspend fun getMealDetailsById(@Query("i") mealId: Long)
}