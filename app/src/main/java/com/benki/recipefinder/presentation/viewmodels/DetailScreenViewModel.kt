package com.benki.recipefinder.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benki.recipefinder.data.repository.RemoteRecipesRepository
import com.benki.recipefinder.data.repository.Response
import com.benki.recipefinder.network.models.details.DetailedMeal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val remoteRecipesRepository: RemoteRecipesRepository) :
    ViewModel() {

    private val _mealResponse =
        MutableStateFlow(
            Response(
                isSuccess = false,
                data = DetailedMeal(),
                errorMessage = null,
                loading = true
            )
        )
    val mealResponse = _mealResponse.asStateFlow()

    fun getDetails(mealId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data: Response<DetailedMeal> = remoteRecipesRepository.getMealById(mealId)
            if (data.isSuccess) {
                _mealResponse.update {
                    it.copy(
                        isSuccess = true,
                        loading = false,
                        data = data.data
                    )
                }
            } else {
                _mealResponse.update {
                    it.copy(
                        isSuccess = false,
                        loading = false,
                        data = DetailedMeal(),
                        errorMessage = "Something went wrong try again later!!"
                    )
                }
            }
        }
    }
}