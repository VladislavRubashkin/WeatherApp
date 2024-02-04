package com.example.weatherapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.weatherapp.data.api.ApiFactory
import com.example.weatherapp.data.database.AppDatabase
import com.example.weatherapp.data.mapper.Mapper
import com.example.weatherapp.domain.entity.WeatherDay
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.presentation.utils.Constants

class WeatherRepositoryImpl(
    private val application: Application
    ): WeatherRepository {

    private val apiFactory = ApiFactory.apiService
    private val weatherDao = AppDatabase.getInstance(application).weatherDao()
    private val mapper = Mapper()

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
        val modelDto = apiFactory.getWeatherInfo(
            Constants.API_KEY,
            city,
            Constants.DAYS,
            Constants.AQI,
            Constants.ALERTS
        )
        weatherDao.insertWeatherDay(mapper.mapWeatherDataDtoToListWeatherDayDbModel(modelDto))
    }

}