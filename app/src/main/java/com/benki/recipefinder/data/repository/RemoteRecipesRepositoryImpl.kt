package com.benki.recipefinder.data.repository

import com.benki.recipefinder.network.models.MealApi
import com.benki.recipefinder.network.models.details.DetailedMeal
import com.benki.recipefinder.network.models.filters.FilterByMainIngredient
import com.benki.recipefinder.network.models.filters.FilterByMainIngredientWrapper
import com.benki.recipefinder.network.models.lists.MealCategories
import com.benki.recipefinder.network.models.random.RandomMeal
import com.benki.recipefinder.network.models.search.SearchByName

data class Response<T>(
    val isSuccess: Boolean,
    val loading: Boolean = false,
    val errorMessage: String?,
    val data: T
)

class RemoteRecipesRepositoryImpl(private val mealApi: MealApi) : RemoteRecipesRepository {
    override suspend fun getRandomMeal(): Response<RandomMeal> {
        return try {
            val randomMeal = mealApi.getRandomMeal()
            Response(isSuccess = true, errorMessage = null, data = randomMeal)
        } catch (e: Exception) {
            Response(isSuccess = false, errorMessage = e.message, data = RandomMeal())
        }
    }

    override suspend fun getMealById(id: String): Response<DetailedMeal> {
        return try {
            val meal = mealApi.getMealDetailsById(id)
            Response(isSuccess = true, errorMessage = null, data = meal)
        } catch (e: Exception) {
            Response(isSuccess = false, errorMessage = e.message, data = DetailedMeal())
        }
    }

    override suspend fun getMealByName(name: String): Response<SearchByName> {
        return try {
            val mealByName = mealApi.getMealsByName(name)
            Response(isSuccess = true, errorMessage = null, data = mealByName)
        } catch (e: Exception) {
            Response(isSuccess = false, errorMessage = e.message, data = SearchByName())
        }
    }

    override suspend fun getMealsCategories(): Response<MealCategories> {
        return try {
            val categories = mealApi.getMealsCategories()
            Response(isSuccess = true, errorMessage = null, data = categories)
        } catch (e: Exception) {
            Response(isSuccess = false, errorMessage = e.message, data = MealCategories())
        }
    }

    override suspend fun getMealsByMainIngredient(ingredient: String): Response<List<FilterByMainIngredient>> {
        return try {
            val mealByMainIngredient = mealApi.getMealsByMainIngredient(ingredient)
            if (mealByMainIngredient.meals != null) {
                Response(isSuccess = true, errorMessage = null, data = mealByMainIngredient.meals)
            } else {
                Response(
                    isSuccess = false,
                    errorMessage = "Sorry recipes found",
                    data = emptyList()
                )
            }
        } catch (e: Exception) {
            Response(
                isSuccess = false,
                errorMessage = e.message,
                data = emptyList()
            )
        }
    }
}