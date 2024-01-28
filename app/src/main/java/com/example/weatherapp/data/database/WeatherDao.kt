package com.example.weatherapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_day_items")
    fun getWeatherWeek(): LiveData<List<WeatherDayDbModel>>

    @Query("SELECT * FROM WEATHER_DAY_ITEMS WHERE id=:dayId")
    fun getWeatherDay(dayId: Int): WeatherDayDbModel

    @Insert
    fun insertWeatherDay(weatherDayDbModel: WeatherDayDbModel)

    @Query("DELETE FROM weather_day_items WHERE id=:dayId")
    fun deleteWeatherDay(dayId: Int)
}