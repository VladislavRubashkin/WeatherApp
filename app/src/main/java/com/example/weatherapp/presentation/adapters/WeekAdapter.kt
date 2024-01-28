package com.example.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherapp.databinding.ItemDayWeatherBinding
import com.example.weatherapp.domain.entity.WeatherDay

class WeekAdapter: ListAdapter<WeatherDay, DayViewHolder>(DayDiffUtilCallback()) {

    var dayClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val binding = ItemDayWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int)  {
        val day = getItem(position)
        val binding = holder.binding
        with(binding) {
            root.setOnClickListener {
                dayClickListener?.invoke(day.id)
            }
            tvDate.text = day.date
            tvTemp.text = day.currentTemp
            tvCondition.text = day.condition
            // TODO Возможно загрузка в другом месте
//            Picasso.get().load("https:" + day.imageUrl).into(ivCondition)
        }
    }
}