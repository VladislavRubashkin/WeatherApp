package com.example.weatherapp.domain.entity

data class WeatherDay(
    val id: Int,
    val city: String,
    val date: String,
    val condition: String,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val imageUrl: String
)
