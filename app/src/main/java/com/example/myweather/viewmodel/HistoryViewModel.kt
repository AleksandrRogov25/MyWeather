package com.example.myweather.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.model.DetailsRepositoryRoomImpl
import com.example.myweather.model.Weather

class HistoryViewModel(
    val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: DetailsRepositoryRoomImpl = DetailsRepositoryRoomImpl()
) : ViewModel() {
    fun getData(): LiveData<AppState> {
        return liveDataToObserver
    }

    fun getAll() {
        repository.getAllWeatherDetails(object : CallbackForAll {
            override fun onResponse(listWeather: List<Weather>) {
                liveDataToObserver.postValue(AppState.Success(listWeather))
            }

        })
    }

    interface CallbackForAll {
        fun onResponse(listWeather: List<Weather>)
    }


}


