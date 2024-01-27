package com.example.weatherapp.presentation.viewmodel


import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.entity.Day
import com.example.weatherapp.domain.usecases.GetWeatherDayUseCase

class DayViewModel : ViewModel() {

    private val repo = WeatherRepositoryImpl()
    private val getWeatherDayUseCase = GetWeatherDayUseCase(repo)

    private val _weatherDay = MutableLiveData<Day>()
    val weatherDay: LiveData<Day>
        get() = _weatherDay


    fun getWeatherDay(dayId: Int) {
        val weatherDay = getWeatherDayUseCase(dayId)
        _weatherDay.value = weatherDay
    }

}