package com.example.myweather.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.model.Repository
import com.example.myweather.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {
    fun getData() = liveDataToObserver


    fun getWeatherRussia() = getWeather(true)
    fun getWeatherWorld() = getWeather(false)

    fun getWeather(isRus: Boolean) {
        Thread {
            liveDataToObserver.postValue(AppState.Loading)
            sleep(2000)
            val answer =
                if (isRus) repository.getWeatherFromLocalStorageRus() else repository.getWeatherFromLocalStorageWorld()
            liveDataToObserver.postValue(AppState.Success(answer))
        }
            .start()
    }

}


