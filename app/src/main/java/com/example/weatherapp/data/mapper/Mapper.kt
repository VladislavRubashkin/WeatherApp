package com.example.weatherapp.data.mapper

import android.util.Log
import com.example.weatherapp.data.api.models.HoursDto
import com.example.weatherapp.data.api.models.WeatherDataDto
import com.example.weatherapp.data.database.models.HoursDbModel
import com.example.weatherapp.data.database.models.WeatherDayDbModel
import com.example.weatherapp.domain.entity.HoursWeatherDay
import com.example.weatherapp.domain.entity.WeatherDay
import javax.inject.Inject

class Mapper @Inject constructor() {

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
            hours = mapListHoursDbModelToListHoursEntity(weatherDayDbModel.hours)
        )
    }

    fun mapListDbModelToListEntity(weatherDayDbModelList: List<WeatherDayDbModel>) = weatherDayDbModelList.map {
        mapDayDbModelToEntity(it)
    }

    fun mapWeatherDataDtoToListWeatherDayDbModel(weatherDataDto: WeatherDataDto): List<WeatherDayDbModel> {
        val weatherDayList = mutableListOf<WeatherDayDbModel>()
        for (i in 0 until 3) {
            Log.d("LOG_TAG", "${weatherDataDto.forecast.forecastday[0].day.hour}")
            val weatherDay = WeatherDayDbModel(
                id = i,
                city = weatherDataDto.location.name,
                date = weatherDataDto.forecast.forecastday[i].date,
                condition = weatherDataDto.forecast.forecastday[i].day.condition.text,
                currentTemp = weatherDataDto.forecast.forecastday[i].day.avgtemp_c.toString(),
                maxTemp = weatherDataDto.forecast.forecastday[i].day.maxtemp_c.toString(),
                minTemp = weatherDataDto.forecast.forecastday[i].day.mintemp_c.toString(),
                imageUrl = weatherDataDto.forecast.forecastday[i].day.condition.icon,
                hours = mapListHoursDtoToListHoursDbModel(weatherDataDto.forecast.forecastday[i].day.hour)
                )
            weatherDayList.add(weatherDay)
        }
        return weatherDayList
    }

    private fun mapHoursDtoToHoursDbModel(hoursDto: HoursDto): HoursDbModel {
        return HoursDbModel(
            id = 0,
            time = hoursDto.time,
            temp = hoursDto.temp_c,
            conditionDescription = hoursDto.condition.text,
            conditionIcon = hoursDto.condition.icon
        )
    }

    private fun mapListHoursDtoToListHoursDbModel(hours: List<HoursDto>) = hours.map {
        mapHoursDtoToHoursDbModel(it)
    }

    private fun mapHoursDbModelToHoursEntity(hours: HoursDbModel): HoursWeatherDay {
        return HoursWeatherDay(
            id = hours.id,
            time = hours.time,
            temp = hours.temp,
            conditionDescription = hours.conditionDescription,
            conditionIcon = hours.conditionIcon
        )
    }

    private fun mapListHoursDbModelToListHoursEntity(hours: List<HoursDbModel>) = hours.map {
        mapHoursDbModelToHoursEntity(it)
    }
}