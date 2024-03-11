package com.benki.recipefinder.network.models.lists

import com.google.gson.annotations.SerializedName

data class ListByCategory(
    @SerializedName("strCategory" ) val strCategory : String? = null
)
