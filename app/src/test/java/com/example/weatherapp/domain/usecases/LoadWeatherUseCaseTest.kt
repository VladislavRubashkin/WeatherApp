package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

class LoadWeatherUseCaseTest {

    private val weatherRepositoryTest = mock<WeatherRepository>()

    @Test
    fun `when invoke LoadWeatherUseCase, it should call loadWeather in repository`() {
        runBlocking {
            //arrange
            val city = "Moscow"

            // act
            LoadWeatherUseCase(weatherRepositoryTest).invoke(city)

            // assert
            Mockito.verify(weatherRepositoryTest, Mockito.times(1)).loadWeather(city)
        }
    }

    @After
    fun after() {
        Mockito.reset(weatherRepositoryTest)
    }
}