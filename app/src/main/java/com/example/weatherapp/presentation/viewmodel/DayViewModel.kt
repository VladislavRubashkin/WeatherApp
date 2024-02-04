package com.example.weatherapp.presentation.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.usecases.GetWeatherDayUseCase

class DayViewModel(
    private val application: Application
    ) : AndroidViewModel(application) {

    private val repo = WeatherRepositoryImpl(application)
    private val getWeatherDayUseCase = GetWeatherDayUseCase(repo)

    fun getWeatherDay(dayId: Int) = getWeatherDayUseCase(dayId)

}