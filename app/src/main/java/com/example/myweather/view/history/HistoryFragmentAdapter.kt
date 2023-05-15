package com.example.myweather.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.example.myweather.databinding.FragmentHistoryRecycleItemBinding
import com.example.myweather.model.Weather
class HistoryFragmentAdapter() :
    RecyclerView.Adapter<HistoryFragmentAdapter.RecyclerItemViewHolder>() {

    private var weatherData: List<Weather> = listOf()

    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {

        val binding = FragmentHistoryRecycleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecyclerItemViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(weatherData.get(position))
    }

    override fun getItemCount() = weatherData.size

    inner class RecyclerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: Weather) {
            FragmentHistoryRecycleItemBinding.bind(itemView).apply {
                tvCityName.text = weather.city.name
                tvTemperature.text = weather.temperature.toString()
                tvFeelsLike.text = weather.feelsLike.toString()
                tvIcon.loadSvg("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.icon}.svg")
            }
        }

        fun ImageView.loadSvg(url: String) {
            ImageLoader.Builder(this.context)
                .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
                .build()
        }
    }
}