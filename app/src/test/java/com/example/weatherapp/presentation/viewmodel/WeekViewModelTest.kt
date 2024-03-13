package com.example.weatherapp.presentation.viewmodel

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.weatherapp.domain.usecases.GetWeatherWeekUseCase
import com.example.weatherapp.domain.usecases.LoadWeatherUseCase
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class WeekViewModelTest {

    private val getWeatherWeekUseCase = mock<GetWeatherWeekUseCase>()
    private val loadWeatherUseCase = mock<LoadWeatherUseCase>()
    private val testWeekViewModel = WeekViewModel(
        getWeatherWeekUseCase = getWeatherWeekUseCase,
        loadWeatherUseCase = loadWeatherUseCase
    )

    @Test
    fun `method requestWeatherData should be response not null`() {
        runBlocking {
            val testCity = "Moscow"
//            Mockito.`when`(testWeekViewModel.requestWeatherData(testCity)).thenReturn()

            testWeekViewModel.requestWeatherData(testCity)
            Mockito.verify(loadWeatherUseCase, times(1)).invoke(testCity)
        }
    }

    @BeforeEach
    fun before() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    @AfterEach
    fun after() {
        Mockito.reset(testWeekViewModel)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}