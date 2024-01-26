package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repository.WeatherRepository

class GetWeatherWeekUseCase(private val repository: WeatherRepository) {

    operator fun invoke() = repository.getWeatherWeek()
}