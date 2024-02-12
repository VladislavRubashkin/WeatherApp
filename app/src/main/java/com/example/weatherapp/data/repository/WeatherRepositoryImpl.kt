package com.example.weatherapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.weatherapp.data.api.ApiService
import com.example.weatherapp.data.database.WeatherDao
import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.domain.entity.WeatherDay
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.presentation.utils.Constants
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val weatherDao: WeatherDao,
    private val mapper: Mapper
) : WeatherRepository {


    override fun getWeatherDay(dayId: Int): LiveData<WeatherDay> = MediatorLiveData<WeatherDay>().apply {
        addSource(weatherDao.getWeatherDay(dayId)) {
            value = mapper.mapDayDbModelToEntity(it)
        }
    }

    override fun getWeatherWeek(): LiveData<List<WeatherDay>> = MediatorLiveData<List<WeatherDay>>().apply {
        addSource(weatherDao.getWeatherWeek()) {
            value = mapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun loadWeather(city: String) {
        val modelDto = apiService.getWeatherInfo(
            Constants.API_KEY,
            city,
            Constants.DAYS,
            Constants.AQI,
            Constants.ALERTS
        )
        weatherDao.insertWeatherDay(mapper.mapWeatherDataDtoToListWeatherDayDbModel(modelDto))
    }
}