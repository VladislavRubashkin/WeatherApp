package com.example.weatherapp.data.database

import androidx.room.TypeConverter
import com.example.weatherapp.data.database.models.HoursDbModel

class HoursConverter {

    @TypeConverter
    fun convertListHoursToString(hoursDbModel: List<HoursDbModel>): String {
        val sb = StringBuilder()
        for (i in hoursDbModel.indices) {
            sb
                .append(hoursDbModel[i].id).append(",")
                .append(hoursDbModel[i].time).append(",")
                .append(hoursDbModel[i].temp).append(",")
                .append(hoursDbModel[i].conditionDescription).append(",")
                .append(hoursDbModel[i].conditionIcon).append(";")
        }
        return sb.toString()
    }

    @TypeConverter
    fun convertStringToHours(strHours: String): List<HoursDbModel> {
        val hoursList = mutableListOf<HoursDbModel>()
        strHours.split(";").forEach {
            val strList = it.split(",")
            val hoursDbModel = HoursDbModel(
                id = strList[0].let { this.toString().toInt() },
                time = strList[1],
                temp = strList[2].toFloat(),
                conditionDescription = strList[3],
                conditionIcon = strList[4]
            )
            hoursList.add(hoursDbModel)
        }
        return hoursList
    }
}