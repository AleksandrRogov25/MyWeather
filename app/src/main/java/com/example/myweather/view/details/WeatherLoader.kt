package com.example.myweather.view.details

import android.os.Handler
import android.util.Log
import com.example.myweather.model.WeatherDTO
import com.example.myweather.BuildConfig.WEATHER_API_KEY
import com.google.gson.Gson
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(
    private val listener: WeatherLoaderListener,
    private val lat: Double,
    private val lon: Double
) {


    fun loadWeather() {

        try {
            val uri =
                URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}&lang=ru_RU")

            val handler = Handler()

            Thread {
                lateinit var urlConnection: HttpsURLConnection

                try {

                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 1000
                    urlConnection.addRequestProperty("X-Yandex-API-Key", WEATHER_API_KEY)


                    val weatherDTO: WeatherDTO =
                        Gson().fromJson(
                            urlConnection.inputStream.bufferedReader(),
                            WeatherDTO::class.java
                        )

                    handler.post {
                        listener.onLoaded(weatherDTO)
                    }

                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()

                    handler.post {
                        listener.onFailed(e)
                    }

                } finally {
                    urlConnection.disconnect()
                }
            }
                .start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
        }
    }


    interface WeatherLoaderListener {
        fun onLoaded(weatherDTO: WeatherDTO)
        fun onFailed(throwable: Throwable)
    }
}