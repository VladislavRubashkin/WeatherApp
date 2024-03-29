package com.example.weatherapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.domain.entity.WeatherDay

interface WeatherRepository {

    fun getWeatherDay(dayId: Int): LiveData<WeatherDay>

    fun getWeatherWeek(): LiveData<List<WeatherDay>>

    suspend fun loadWeather(city: String)
}