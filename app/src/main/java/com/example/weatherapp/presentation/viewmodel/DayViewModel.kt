package com.example.weatherapp.presentation.viewmodel


import androidx.lifecycle.ViewModel
import com.example.weatherapp.domain.usecases.GetWeatherDayUseCase
import javax.inject.Inject

class DayViewModel @Inject constructor(
    private val getWeatherDayUseCase: GetWeatherDayUseCase
) : ViewModel() {

    fun getWeatherDay(dayId: Int) = getWeatherDayUseCase(dayId)
}