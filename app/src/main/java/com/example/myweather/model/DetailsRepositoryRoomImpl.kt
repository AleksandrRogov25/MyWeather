package com.example.myweather.model


import com.example.myweather.MyApp
import com.example.myweather.model.utils.convertHistoryEntityToWeather
import com.example.myweather.model.utils.convertWeatherToEntity
import com.example.myweather.viewmodel.DetailsViewModel
import com.example.myweather.viewmodel.HistoryViewModel


class DetailsRepositoryRoomImpl : DetailsRepository, DetailsRepositoryAdd, DetailsRepositoryForAll {
    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val list = convertHistoryEntityToWeather(MyApp.getHistoryDao().getHistoryForCity(city.name))
        callback.onResponse(list.last())
    }

    override fun getAllWeatherDetails(callback: HistoryViewModel.CallbackForAll) {
        callback.onResponse(convertHistoryEntityToWeather(MyApp.getHistoryDao().getAll()))
    }

    override fun addWeather(weather: Weather) {
        MyApp.getHistoryDao().insert(convertWeatherToEntity(weather))
    }


}

