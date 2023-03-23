package com.example.myweather.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.myweather.model.Weather
import kotlinx.android.synthetic.main.fragment_main_recycle_item.view.*

class MainFragmentAdapter(private var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    interface OnItemViewClickListener {
        fun onItemClick(weather: Weather)
    }

    private var weatherData: List<Weather> = listOf()

    fun setWeather(data: Weather) {
        weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_main_recycle_item,
                parent,
                false
            ) as View
        )
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(weatherData.get(position))
    }

    override fun getItemCount() = weatherData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(weather: Weather) {
            itemView.apply {
                mainFragmentRecyclerItemTextView.text = weather.city.city
                setOnClickListener { onItemViewClickListener?.onItemClick(weather) }
                }
            }
        }
    }
