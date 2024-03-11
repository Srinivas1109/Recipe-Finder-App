package com.benki.recipefinder.network.models.lists

import com.google.gson.annotations.SerializedName

data class ListByIngredient(
    @SerializedName("idIngredient") val idIngredient: String? = null,
    @SerializedName("strIngredient") val strIngredient: String? = null,
    @SerializedName("strDescription") val strDescription: String? = null,
    @SerializedName("strType") val strType: String? = null
)
