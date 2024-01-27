package com.example.weatherapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.domain.entity.Day

interface WeatherRepository {

    fun getWeatherDay(dayId: Int): Day

    fun getWeatherWeek(): LiveData<List<Day>>

    fun loadWeather()
}