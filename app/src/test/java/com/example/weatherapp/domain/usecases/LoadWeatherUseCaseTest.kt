package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class LoadWeatherUseCaseTest {

    private val weatherRepositoryTest = mock<WeatherRepository>()

    @Test
    fun `when invoke LoadWeatherUseCase, it should call loadWeather in repository`() = runTest {
        //arrange
        val city = "Moscow"

        // act
        LoadWeatherUseCase(weatherRepositoryTest).invoke(city)

        // assert
        Mockito.verify(weatherRepositoryTest, Mockito.times(1)).loadWeather(city)
    }

    @AfterEach
    fun after() {
        Mockito.reset(weatherRepositoryTest)
    }
}