package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.usecases.GetWeatherWeekUseCase
import com.example.weatherapp.domain.usecases.LoadWeatherUseCase
import kotlinx.coroutines.launch

class WeekViewModel : ViewModel() {

    private val repo = WeatherRepositoryImpl()
    private val getWeatherWeekUseCase = GetWeatherWeekUseCase(repo)
    private val loadWeatherUseCase = LoadWeatherUseCase(repo)

    val weekWeather = getWeatherWeekUseCase()

    init {
        viewModelScope.launch {
            loadWeatherUseCase()
        }
    }
}