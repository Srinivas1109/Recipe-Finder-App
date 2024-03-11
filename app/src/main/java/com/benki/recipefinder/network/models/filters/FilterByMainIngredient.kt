package com.benki.recipefinder.network.models.filters

import com.google.gson.annotations.SerializedName

data class FilterByMainIngredient(
    @SerializedName("strMeal") val strMeal: String? = null,
    @SerializedName("strMealThumb") val strMealThumb: String? = null,
    @SerializedName("idMeal") val idMeal: String? = null
)
