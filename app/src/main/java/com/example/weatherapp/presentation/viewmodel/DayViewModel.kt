package com.example.weatherapp.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.entity.WeatherDay
import com.example.weatherapp.domain.usecases.GetWeatherDayUseCase

class DayViewModel : ViewModel() {

    private val repo = WeatherRepositoryImpl()
    private val getWeatherDayUseCase = GetWeatherDayUseCase(repo)

    private val _weatherWeatherDay = MutableLiveData<WeatherDay>()
    val weatherDay: LiveData<WeatherDay>
        get() = _weatherWeatherDay


    fun getWeatherDay(dayId: Int) {
        val weatherDay = getWeatherDayUseCase(dayId)
        _weatherWeatherDay.value = weatherDay
    }

}