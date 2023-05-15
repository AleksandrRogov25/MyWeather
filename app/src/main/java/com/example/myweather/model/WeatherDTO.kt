package com.example.myweather.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDTO(
    val fact: FactDTO
) : Parcelable


fun convertDtoToModel(weatherDTO: WeatherDTO): Weather {
    val fact: FactDTO = weatherDTO.fact!!
    return Weather(
        getDefaultCity(),
        fact.temp!!,
        fact.feels_like!!,
        fact.condition!!,
        fact.icon
    )
}
