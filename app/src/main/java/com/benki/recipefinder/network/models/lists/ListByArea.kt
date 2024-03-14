package com.benki.recipefinder.network.models.lists

import com.google.gson.annotations.SerializedName

data class ListByArea(
    @SerializedName("strArea") val strArea: String? = null
)
