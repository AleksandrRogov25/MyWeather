package com.example.myweather.model


import com.example.myweather.viewmodel.HistoryViewModel

interface DetailsRepositoryForAll {
    fun getAllWeatherDetails(callback: HistoryViewModel.CallbackForAll)
}