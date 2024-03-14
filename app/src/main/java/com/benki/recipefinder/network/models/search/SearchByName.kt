package com.benki.recipefinder.network.models.search

import com.benki.recipefinder.network.models.details.Meal

data class SearchByName(val meals: List<Meal>? = emptyList())
