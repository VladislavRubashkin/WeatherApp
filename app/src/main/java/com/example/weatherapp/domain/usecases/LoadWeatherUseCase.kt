package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repository.WeatherRepository

class LoadWeatherUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(city: String) = repository.loadWeather(city)
}