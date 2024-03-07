package com.example.weatherapp.data.api.models

data class WeatherDataDto(
    val location: LocationDto,
    val forecast: ForecastDto
)

data class LocationDto(
    val name: String,
    val localtime: String
)

data class ConditionDto(
    val text: String,
    val icon: String
)

data class ForecastDto(
    val forecastday: List<ForecastDayDto>
)

data class ForecastDayDto(
    val date: String,
    val day: DayDto
)

data class DayDto(
    val avgtemp_c: Float,
    val maxtemp_c: Float,
    val mintemp_c: Float,
    val condition: ConditionDto,
)





