package com.example.weatherapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.domain.entity.Day
import com.example.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl: WeatherRepository {

    private val dayItem = mutableListOf<Day>()
    private val dayListLiveData = MutableLiveData<List<Day>>()

    init {
        for (i in 0..10) {
            val day = Day(
                id = i,
                city = "St. Petersburg",
                "31/10/022 $i",
                condition = "Sunny $i",
                currentTemp = "20°C $i",
                maxTemp = "maxTemp $i",
                minTemp = "minTemp $i",
                imageUrl = "imageUrl $i",
                hours = "hours $i"
            )
            dayItem.add(day)
        }
        dayListLiveData.value = dayItem
    }

    override fun getWeatherDay(dayId: Int): Day {
        return dayItem.find { it.id == dayId } ?: throw RuntimeException()
    }

    override fun getWeatherWeek(): LiveData<List<Day>> {
        return dayListLiveData
    }

    override fun loadWeather() {

    }
}