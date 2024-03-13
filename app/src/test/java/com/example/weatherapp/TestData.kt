package com.example.weatherapp

import com.example.weatherapp.data.database.models.WeatherDayDbModel
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

    fun getActualWeatherDayDbModel(): WeatherDayDbModel = WeatherDayDbModel(
        id = 1,
        city = "St. Petersburg",
        date = "07.03.2024",
        condition = "condition",
        currentTemp = "0.0",
        maxTemp = "1.0",
        minTemp = "-1.0",
        imageUrl = "image"
    )

    fun getExpectedWeatherDayDbModel(): WeatherDayDbModel = WeatherDayDbModel(
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