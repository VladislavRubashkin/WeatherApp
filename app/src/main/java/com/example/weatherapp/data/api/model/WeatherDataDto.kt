package com.example.weatherapp.data.api.model

data class WeatherDataDto(
    val location: LocationDto,
    val current: CurrentDto,
    val forecast: ForecastDto
)

data class LocationDto(
    val name: String,
    val localtime: String
)

data class CurrentDto (
    val last_updated: String,
    val temp_c: Float,
    val condition: ConditionDto
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
    val maxtemp_c: Float,
    val mintemp_c: Float,
    val condition: ConditionDto,
    val hour: List<HoursDto>
)

data class HoursDto(
    val time: String,
    val temp_c: Float,
//    val condition: ConditionDto
)




