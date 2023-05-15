package com.example.myweather.viewmodel

import com.example.myweather.model.Weather

sealed class DetailsState {
    data class Success(val weather: Weather) : DetailsState()
    data class Error(val error: Throwable) : DetailsState()
    object Loading : DetailsState()
}