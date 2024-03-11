package com.example.weatherapp.domain.utils

import com.example.weatherapp.domain.entity.WeatherDay

object TestData {

    fun getActualWeatherDay(): WeatherDay = WeatherDay(
        id = 1,
        city = "St. Petersburg",
        date = "07.03.2024",
        condition = "condition",
        currentTemp = "0.0",
        maxTemp = "1.0",
        minTemp = "-1.0",
        imageUrl = "image"
    )

    fun getExpectedWeatherDay(): WeatherDay = WeatherDay(
        id = 1,
        city = "St. Petersburg",
        date = "07.03.2024",
        condition = "condition",
        currentTemp = "0.0",
        maxTemp = "1.0",
        minTemp = "-1.0",
        imageUrl = "image"
    )

}