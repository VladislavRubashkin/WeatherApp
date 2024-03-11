package com.example.weatherapp.presentation

import com.example.weatherapp.domain.usecases.GetWeatherWeekUseCase
import com.example.weatherapp.domain.usecases.LoadWeatherUseCase
import com.example.weatherapp.presentation.viewmodel.WeekViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class WeekViewModelTest {

    private val getWeatherWeekUseCase = mock<GetWeatherWeekUseCase>()
    private val loadWeatherUseCase = mock<LoadWeatherUseCase>()

    @Test
    fun `method requestWeatherData should be response not null`() {
        runBlocking {
            val testCity = "Moscow"
            val testWeekViewModel = WeekViewModel(
                getWeatherWeekUseCase = getWeatherWeekUseCase,
                loadWeatherUseCase = loadWeatherUseCase
            )
            testWeekViewModel.requestWeatherData(testCity)
            Mockito.verify(loadWeatherUseCase, times(1)).invoke(testCity)
        }
    }
}