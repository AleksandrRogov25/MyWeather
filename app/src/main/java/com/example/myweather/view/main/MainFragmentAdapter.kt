package com.example.myweather.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.databinding.FragmentMainRecycleItemBinding
import com.example.myweather.model.Weather

class MainFragmentAdapter(private var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    interface OnItemViewClickListener {
        fun onItemClick(weather: Weather)
    }

    private var weatherData: List<Weather> = listOf()

    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = FragmentMainRecycleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(weatherData.get(position))
    }

    override fun getItemCount() = weatherData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(weather: Weather) {
            val binding = FragmentMainRecycleItemBinding.bind(itemView)
            binding.tvCityName.text = weather.city.city
            binding.tvCityName.setOnClickListener {
                onItemViewClickListener?.onItemClick(weather)
            }
        }
    }
}