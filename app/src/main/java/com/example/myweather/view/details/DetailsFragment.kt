package com.example.myweather.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.example.myweather.R
import com.example.myweather.databinding.FragmentDetailsBinding
import com.example.myweather.model.City
import com.example.myweather.model.Weather
import com.example.myweather.model.WeatherAPI
import com.example.myweather.model.WeatherDTO
import com.example.myweather.viewmodel.DetailsState
import com.example.myweather.viewmodel.DetailsViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by lazy { ViewModelProvider(this).get(DetailsViewModel::class.java) }
    private lateinit var weather: Weather




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            viewModel.getLiveData().observe(viewLifecycleOwner, object :Observer<DetailsState> {
                override fun onChanged(t: DetailsState) {
                    renderData(t)
                }
            })
            arguments?.getParcelable<Weather>(BUNDLE_EXTRA) ?.let {
                viewModel.getWeather(it.city)
            }


            }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
       }

    companion object {
        const val BUNDLE_EXTRA = "weather"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


            private fun renderData(detailsState: DetailsState) {
        when (detailsState) {
            is DetailsState.Success -> {
                val weather = detailsState.weather
                with(binding){

                    loadingLayout.visibility = View.GONE
                    cityName.text = weather.city.name
                    temperatureValue.text = weather.temperature.toString()
                    feelsLikeValue.text = weather.feelsLike.toString()
                    cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"
                    Snackbar.make(mainView,"Получилось", Snackbar.LENGTH_LONG)
                        .show()

                    Picasso.get()?.load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
                        ?.into(headerIcon)


                    icon.loadSvg ("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.icon}.svg")

                }


            }
            is DetailsState.Loading -> {
            }
            is DetailsState.Error -> {

            }
        }
    }
    fun ImageView.loadSvg(url: String) {
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .data(url)
            .target(this)
            .build()
        imageLoader.enqueue(request)
    }
}
