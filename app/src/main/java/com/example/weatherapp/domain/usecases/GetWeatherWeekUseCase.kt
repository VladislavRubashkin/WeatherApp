package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherWeekUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    operator fun invoke() = repository.getWeatherWeek()
}