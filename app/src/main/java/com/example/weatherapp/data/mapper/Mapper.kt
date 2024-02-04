package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.api.model.HoursDto
import com.example.weatherapp.data.api.model.WeatherDataDto
import com.example.weatherapp.data.database.models.HoursDbModel
import com.example.weatherapp.data.database.models.WeatherDayDbModel
import com.example.weatherapp.domain.entity.WeatherDay

class Mapper {

    fun mapDayDbModelToEntity(weatherDayDbModel: WeatherDayDbModel): WeatherDay {
        return WeatherDay(
            id = weatherDayDbModel.id,
            city = weatherDayDbModel.city,
            date = weatherDayDbModel.date,
            condition = weatherDayDbModel.condition,
            currentTemp = weatherDayDbModel.currentTemp,
            maxTemp = weatherDayDbModel.maxTemp,
            minTemp = weatherDayDbModel.minTemp,
            imageUrl = weatherDayDbModel.imageUrl,
            hours = ""
        )
    }

    fun mapListDbModelToListEntity(weatherDayDbModelList: List<WeatherDayDbModel>) = weatherDayDbModelList.map {
        mapDayDbModelToEntity(it)
    }

    fun mapWeatherDataDtoToListWeatherDayDbModel(weatherDataDto: WeatherDataDto): List<WeatherDayDbModel> {
        val weatherDayList = mutableListOf<WeatherDayDbModel>()
        for (i in 0 until 3) {
            val weatherDay = WeatherDayDbModel(
                id = 0,
                city = weatherDataDto.location.name,
                date = weatherDataDto.forecast.forecastday[i].date,
                condition = weatherDataDto.forecast.forecastday[i].day.condition.text,
                currentTemp = weatherDataDto.current.temp_c.toString(),
                maxTemp = weatherDataDto.forecast.forecastday[i].day.maxtemp_c.toString(),
                minTemp = weatherDataDto.forecast.forecastday[i].day.mintemp_c.toString(),
                imageUrl = weatherDataDto.current.condition.icon,

                )
            weatherDayList.add(weatherDay)
        }
        return weatherDayList
    }

    private fun mapHoursDtoToHoursDbModel(hoursDto: HoursDto): HoursDbModel {
        return HoursDbModel(
            id = 0,
            time = hoursDto.time,
            temp = hoursDto.temp_c
        )
    }
}