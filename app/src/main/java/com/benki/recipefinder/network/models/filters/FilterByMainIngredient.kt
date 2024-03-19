package com.benki.recipefinder.network.models.filters

import com.benki.recipefinder.data.database.model.Meal
import com.google.gson.annotations.SerializedName

data class FilterByMainIngredient(
    @SerializedName("strMeal") val strMeal: String? = null,
    @SerializedName("strMealThumb") val strMealThumb: String? = null,
    @SerializedName("idMeal") val idMeal: String? = null,
    val saved: Boolean = false
)

data class FilterByMainIngredientWrapper(val meals: List<FilterByMainIngredient>? = emptyList())

fun FilterByMainIngredient.toDatabaseMeal(): Meal {
    return Meal(idMeal = idMeal!!, strMeal = strMeal, strMealThumb = strMealThumb, saved = saved)
}