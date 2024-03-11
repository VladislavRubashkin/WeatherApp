package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.database.models.WeatherDayDbModel
import org.junit.Test
import org.junit.jupiter.api.Assertions

class MapperTest {

    @Test
    fun `should return correct data as a result of mapping mapDayDbModelToEntity`() {

        val expected = WeatherDayDbModel(
            id = 1,
            city = "St. Petersburg",
            date = "07.03.2024",
            condition = "condition",
            currentTemp = "0.0",
            maxTemp = "1.0",
            minTemp = "-1.0",
            imageUrl = "image"
        )
        val actual = Mapper().mapDayDbModelToEntity(expected)

        /**
         * val actualTrue = actual.equals(expected) - возвращает false, при том что WeatherDayDbModel() и WeatherDay()
         * data classes
         */

//        val expectedTrue = true
//        val actualTrue = actual.equals(expected)
//
//        Assertions.assertEquals(expectedTrue, actualTrue)

        Assertions.assertEquals(expected.id, actual.id)
        Assertions.assertEquals(expected.city, actual.city)
        Assertions.assertEquals(expected.date, actual.date)
        Assertions.assertEquals(expected.condition, actual.condition)
        Assertions.assertEquals(expected.currentTemp, actual.currentTemp)
        Assertions.assertEquals(expected.maxTemp, actual.maxTemp)
        Assertions.assertEquals(expected.minTemp, actual.minTemp)
        Assertions.assertEquals(expected.imageUrl, actual.imageUrl)
    }

}

