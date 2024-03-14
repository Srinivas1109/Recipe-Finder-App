package com.benki.recipefinder.network.models.lists

import com.google.gson.annotations.SerializedName

data class ListByMealCategory(
    @SerializedName("idCategory") val idCategory: String? = null,
    @SerializedName("strCategory") val strCategory: String? = null,
    @SerializedName("strCategoryThumb") val strCategoryThumb: String? = null,
    @SerializedName("strCategoryDescription") val strCategoryDescription: String? = null
)

data class MealCategories(val categories: List<ListByMealCategory>? = emptyList())
