package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.database.WeatherDayDbModel
import com.example.weatherapp.domain.entity.WeatherDay

class Mapper {

    fun dayDbModelToEntity(weatherDayDbModel: WeatherDayDbModel): WeatherDay {
        return WeatherDay(
            id = weatherDayDbModel.id,
            city = weatherDayDbModel.city,
            date = weatherDayDbModel.date,
            condition = weatherDayDbModel.condition,
            currentTemp = weatherDayDbModel.currentTemp,
            maxTemp = weatherDayDbModel.maxTemp,
            minTemp = weatherDayDbModel.minTemp,
            imageUrl = weatherDayDbModel.imageUrl,
            hours = weatherDayDbModel.hours
        )
    }
}