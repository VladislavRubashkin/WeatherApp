package com.example.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherapp.databinding.ItemDayWeatherBinding
import com.example.weatherapp.domain.entity.WeatherDay
import com.squareup.picasso.Picasso

class WeekAdapter: ListAdapter<WeatherDay, DayViewHolder>(DayDiffUtilCallback()) {

    var dayClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val binding = ItemDayWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int)  {
        val weatherDay = getItem(position)
        val binding = holder.binding
        with(binding) {
            root.setOnClickListener {
                dayClickListener?.invoke(weatherDay.id)
            }
            tvDate.text = weatherDay.date
            tvTemp.text = weatherDay.currentTemp
            tvCondition.text = weatherDay.condition
            Picasso.get().load("https:" + weatherDay.imageUrl).into(ivCondition)
        }
    }
}