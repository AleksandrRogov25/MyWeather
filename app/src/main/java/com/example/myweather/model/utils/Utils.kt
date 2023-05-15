package com.example.myweather.model.utils

import com.example.myweather.domain.room.HistoryEntity
import com.example.myweather.model.*

class Utils {
}

fun convertDtoToModel(weatherDTO: WeatherDTO): Weather {
    val fact: FactDTO = weatherDTO.fact
    return (Weather(getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.icon!!))

}

fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
    return entityList.map {
        Weather(City(it.city, 0.0, 0.0), it.temperature, it.feelsLike, it.icon)
    }
}

fun convertWeatherToEntity(weather: Weather): HistoryEntity {
    return HistoryEntity(0, weather.city.name, weather.temperature, weather.feelsLike, weather.icon)
}





















































































































































