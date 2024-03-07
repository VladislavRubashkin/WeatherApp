package com.example.weatherapp.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_day_items")
data class WeatherDayDbModel (

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "condition")
    val condition: String,

    @ColumnInfo(name = "currentTemp")
    val currentTemp: String,

    @ColumnInfo(name = "maxTemp")
    val maxTemp: String,

    @ColumnInfo(name = "minTemp")
    val minTemp: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String
)