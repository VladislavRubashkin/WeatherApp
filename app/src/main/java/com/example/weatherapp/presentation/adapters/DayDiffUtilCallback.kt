package com.example.weatherapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.domain.entity.WeatherDay

class DayDiffUtilCallback : DiffUtil.ItemCallback<WeatherDay>() {
    override fun areItemsTheSame(oldItem: WeatherDay, newItem: WeatherDay): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WeatherDay, newItem: WeatherDay): Boolean {
        return oldItem == newItem
    }
}