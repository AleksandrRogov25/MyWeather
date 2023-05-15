package com.example.myweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.model.*

class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private var repository: DetailsRepository = DetailsRepositoryRetrofit2Impl(),
    private var repositoryadd: DetailsRepositoryAdd = DetailsRepositoryRoomImpl()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getWeather(city: City) {
        liveData.postValue(DetailsState.Loading)
        repository.getWeatherDetails(city,
            object : Callback {
                override fun onResponse(weather: Weather) {
                    liveData.postValue(DetailsState.Success(weather))
                    repositoryadd.addWeather(weather)
                }
            })
    }

    interface Callback {
        fun onResponse(weather: Weather)
    }


}