package com.example.weatherapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.domain.entity.Day

class DayDiffUtilCallback : DiffUtil.ItemCallback<Day>() {
    override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem == newItem
    }
}