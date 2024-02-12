package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherDayUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    operator fun invoke(dayId: Int) = repository.getWeatherDay(dayId)
}