package com.example.myweather.view.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.myweather.databinding.FragmentDetailsBinding
import com.example.myweather.model.Weather
import com.example.myweather.model.WeatherDTO

@Suppress("DEPRECATION")
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var weather: Weather

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let { intent ->
                intent.getParcelableExtra<WeatherDTO>(KEY_BANDLE_SERVICE_BROADCAST_WEATHER)?.let {
                    onLoadListener.onLoaded(it)
                }
            }
        }
    }
    private val onLoadListener: WeatherLoader.WeatherLoaderListener =
        object : WeatherLoader.WeatherLoaderListener {
            override fun onLoaded(weatherDTO: WeatherDTO) {
                displayWeather(weatherDTO)
            }

            override fun onFailed(throwable: Throwable) {

            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            receiver,
            IntentFilter(KEY_WAVE)
        )
        weather = arguments?.getParcelable<Weather>(BUNDLE_EXTRA) ?: Weather()
        binding.mainView.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE
        //val loader = WeatherLoader(onLoadListener, weather.city.lat, weather.city.lon)
        //loader.loadWeather()
        requireActivity().startService(Intent(requireContext(), DetailsService::class.java).apply {
            putExtra(KEY_BANDLE_LAT, weather.city.lat)
            putExtra(KEY_BANDLE_LON, weather.city.lon)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun displayWeather(weatherDTO: WeatherDTO) {
        with(binding) {
            mainView.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE
            val city = weather.city
            cityName.text = city.city
            cityCoordinates.text = "${city.lat} ${city.lon}"
            weatherCondition.text = weatherDTO.fact?.condition
            temperatureValue.text = weatherDTO.fact?.temp.toString()
            feelsLikeValue.text = weatherDTO.fact?.feels_like.toString()
        }
    }
}