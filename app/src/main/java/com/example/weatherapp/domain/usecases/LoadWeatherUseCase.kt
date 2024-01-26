package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repository.WeatherRepository

class LoadWeatherUseCase(private val repository: WeatherRepository) {

    operator fun invoke() = repository.loadWeather()
}