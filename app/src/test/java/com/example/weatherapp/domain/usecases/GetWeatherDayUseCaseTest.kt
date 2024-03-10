package com.example.weatherapp.domain.usecases

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.domain.entity.WeatherDay
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.utils.GetData
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetWeatherDayUseCaseTest {

    private val weatherRepositoryTest = mock<WeatherRepository>()

    private val actualWeatherDay = GetData.getActualWeatherDay()

    private val _actualWeatherDayLiveData = MutableLiveData<WeatherDay>()
    private val actualWeatherDayLiveData: LiveData<WeatherDay>
        get() = _actualWeatherDayLiveData

    private val expectedWeatherDay = GetData.getExpectedWeatherDay()

    private val _expectedWeatherDayLiveData = MutableLiveData<WeatherDay>()
    private val expectedWeatherDayLiveData: LiveData<WeatherDay>
        get() = _expectedWeatherDayLiveData


    /**
     * Проверяем не исказились ли данные из WeatherRepository, проходя через GetWeatherDayUseCase.
     */

    @Test
    fun `getWeatherDayUseCase should return the same data as in repository`() {
        //arrange
        val testDayId = 1
        _actualWeatherDayLiveData.value = actualWeatherDay
        _expectedWeatherDayLiveData.value = expectedWeatherDay
        Mockito.`when`(weatherRepositoryTest.getWeatherDay(testDayId)).thenReturn(actualWeatherDayLiveData)

        //act
        val expected = expectedWeatherDayLiveData
        val actual = GetWeatherDayUseCase(repository = weatherRepositoryTest).invoke(testDayId)

        //assert
        Assertions.assertEquals(expected.value, actual.value)
        Mockito.verify(weatherRepositoryTest, Mockito.times(1)).getWeatherDay(testDayId)
    }

    @Before
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

    @After
    fun after() {
        Mockito.reset(weatherRepositoryTest)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}