package com.example.weatherapp.data.mapper

import com.example.weatherapp.TestData
import com.example.weatherapp.data.database.models.WeatherDayDbModel
import com.example.weatherapp.domain.repository.WeatherRepository
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MapperTest {

    @Test
    fun `should return correct data as a result of mapping mapDayDbModelToEntity`() {
        val testData = TestData.getActualWeatherDayDbModel()
        val expected = TestData.getExpectedWeatherDayDbModel()

        val actual = Mapper().mapDayDbModelToEntity(testData)

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

    @Test
    fun `should return correct data as a result of mapping mapListDbModelToListEntity`() {
        val testData = mutableListOf(TestData.getActualWeatherDayDbModel())
        val expected = mutableListOf(TestData.getExpectedWeatherDayDbModel())

        val actual = Mapper().mapListDbModelToListEntity(testData)

        for (i in expected.indices) {
            Assertions.assertEquals(expected[i].id, actual[i].id)
            Assertions.assertEquals(expected[i].city, actual[i].city)
            Assertions.assertEquals(expected[i].date, actual[i].date)
            Assertions.assertEquals(expected[i].condition, actual[i].condition)
            Assertions.assertEquals(expected[i].currentTemp, actual[i].currentTemp)
            Assertions.assertEquals(expected[i].maxTemp, actual[i].maxTemp)
            Assertions.assertEquals(expected[i].minTemp, actual[i].minTemp)
            Assertions.assertEquals(expected[i].imageUrl, actual[i].imageUrl)
        }

    }

}

