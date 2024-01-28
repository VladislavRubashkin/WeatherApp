package com.example.weatherapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.domain.entity.WeatherDay

interface WeatherRepository {

    fun getWeatherDay(dayId: Int): WeatherDay

    fun getWeatherWeek(): LiveData<List<WeatherDay>>

    fun loadWeather()
}