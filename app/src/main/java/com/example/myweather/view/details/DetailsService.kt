package com.example.myweather.view.details

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.myweather.BuildConfig
import com.example.myweather.model.WeatherDTO
import com.google.gson.Gson
import java.net.URL
import javax.net.ssl.HttpsURLConnection

const val KEY_BANDLE_LAT = "lat"
const val KEY_BANDLE_LON = "lon"
const val KEY_WAVE = "wave1"
const val KEY_BANDLE_WEATHER = "weather"
const val KEY_BANDLE_SERVICE_BROADCAST_WEATHER = "weather1"


class DetailsService(name: String = "DetailService") : IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val lat = it.getDoubleExtra(KEY_BANDLE_LAT, 0.0)
            val lon = it.getDoubleExtra(KEY_BANDLE_LON, 0.0)


            val uri =
                URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}&lang=ru_RU")

            var urlConnection: HttpsURLConnection = uri.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 1000
            urlConnection.addRequestProperty(
                "X-Yandex-API-Key",
                BuildConfig.WEATHER_API_KEY
            )

            val weatherDTO: WeatherDTO =
                Gson().fromJson(
                    urlConnection.inputStream.bufferedReader(),
                    WeatherDTO::class.java
                )

            val massage = Intent(KEY_WAVE)
            massage.putExtra(KEY_BANDLE_SERVICE_BROADCAST_WEATHER, weatherDTO)
            LocalBroadcastManager.getInstance(this).sendBroadcast(massage)

        }

    }
}