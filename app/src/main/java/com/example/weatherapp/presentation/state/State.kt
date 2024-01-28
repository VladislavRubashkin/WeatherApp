package com.example.weatherapp.presentation.state

import com.example.weatherapp.domain.entity.WeatherDay

sealed class State

object Error: State()
object Load: State()
class Weather(val value: WeatherDay): State()