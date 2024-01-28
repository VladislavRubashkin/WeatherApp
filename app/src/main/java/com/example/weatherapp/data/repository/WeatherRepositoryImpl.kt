package com.example.weatherapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.domain.entity.WeatherDay
import com.example.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl: WeatherRepository {

    private val weatherDayItem = mutableListOf<WeatherDay>()
    private val weatherDayListLiveData = MutableLiveData<List<WeatherDay>>()

    init {
        for (i in 0..20) {
            val weatherDay = WeatherDay(
                id = i,
                city = "St. Petersburg",
                "31/10/2022 $i",
                condition = "Sunny $i",
                currentTemp = "20°C $i",
                maxTemp = "maxTemp $i",
                minTemp = "minTemp $i",
                imageUrl = "imageUrl $i",
                hours = "hours $i"
            )
            weatherDayItem.add(weatherDay)
        }
        weatherDayListLiveData.value = weatherDayItem
    }

    override fun getWeatherDay(dayId: Int): WeatherDay {
        return weatherDayItem.find { it.id == dayId } ?: throw RuntimeException()
    }

    override fun getWeatherWeek(): LiveData<List<WeatherDay>> {
        return weatherDayListLiveData
    }

    override fun loadWeather() {

    }
}