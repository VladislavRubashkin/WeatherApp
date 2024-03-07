package com.example.weatherapp.data.api

import com.example.weatherapp.data.api.models.WeatherDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast.json")
    suspend fun getWeatherInfo(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") days: String,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String,
    ): WeatherDataDto
}