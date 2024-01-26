package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repository.WeatherRepository

class GetWeatherDayUseCase(private val repository: WeatherRepository) {

    operator fun invoke(dayId: Int) = repository.getWeatherDay(dayId)
}