package com.example.myweather.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    var city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0,
    val icon: String = "bkn_n"
) : Parcelable

fun getDefaultCity() = City("Москва", 55.755826, 37.617299900000035)

fun getWorldCities() = listOf(
    Weather(City("Анкара", 39.9322, 32.8120), 5, 7),
    Weather(City("Гавана", 23.3012, -82.3343), 23, 25),
    Weather(City("Бангкок", 13.7636, 100.5225), 28, 28),
    Weather(City("Пекин", 39.8952, 116.3961), 2, -2),
)

fun getRussianCities() = listOf(
    Weather(City("Москва", 55.755826, 37.617299900000035), -17, -22),
    Weather(City("Санкт-Петербург", 59.9342802, 30.335098600000038), -17, -26),
    Weather(City("Тула", 54.1921, 37.6160), -15, -17),
    Weather(City("Сочи", 43.5859, 39.7221), 4, 3),
)