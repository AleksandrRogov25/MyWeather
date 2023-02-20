package com.example.myweather.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.model.Repository
import com.example.myweather.model.RepositoryImpl
import com.example.myweather.model.Weather
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {
    fun getData() = liveDataToObserver

    fun getWeather() {
        Thread {
            liveDataToObserver.postValue(AppState.Loading)
            sleep(2000)
            liveDataToObserver.postValue(AppState.Success(Weather()))
        }
            .start()
    }

}


