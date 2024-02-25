package com.example.weatherapp.domain.entity

data class HoursWeatherDay(
    val id: Int,
    val time: String,
    val temp: Float,
    val conditionDescription: String,
    val conditionIcon: String
)
