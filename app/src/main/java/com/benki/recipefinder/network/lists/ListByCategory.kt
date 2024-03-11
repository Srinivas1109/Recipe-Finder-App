package com.benki.recipefinder.network.lists

import com.google.gson.annotations.SerializedName

data class ListByCategory(
    @SerializedName("strCategory" ) val strCategory : String? = null
)
