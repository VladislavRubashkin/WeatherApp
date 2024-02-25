package com.example.weatherapp.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("weather_hours_day")
data class HoursDbModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo("time")
    val time: String,

    @ColumnInfo("temp")
    val temp: Float,

    @ColumnInfo(name = "conditionDescription")
    val conditionDescription: String,

    @ColumnInfo(name = "conditionIcon")
    val conditionIcon: String
)
