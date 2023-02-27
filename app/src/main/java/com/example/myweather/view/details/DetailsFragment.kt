package com.example.myweather.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myweather.R
import com.example.myweather.databinding.FragmentDetailsBinding
import com.example.myweather.model.Weather

@Suppress("DEPRECATION")
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(BUNDLE_EXTRA)?.let { weather ->
            weather.city.also {
                with(binding) {
                    cityName.text = it.city
                    cityCoordinates.text = String.format(
                        getString(R.string.city_coordinates),
                        it.lat.toString(),
                        it.lon.toString()
                    )
                    temperatureValue.text = weather.temperature.toString()
                    feelsLikeValue.text = weather.feelsLike.toString()
                }
            }
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
}