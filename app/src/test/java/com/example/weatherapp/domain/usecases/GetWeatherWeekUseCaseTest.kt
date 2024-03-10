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

class GetWeatherWeekUseCaseTest {

    private val weatherRepositoryTest = mock<WeatherRepository>()

    private val actualListWeatherWeek = mutableListOf(
        GetData.getActualWeatherDay()
    )
    private val _actualWeatherWeekLiveData = MutableLiveData<List<WeatherDay>>()
    private val actualWeatherWeekLiveData: LiveData<List<WeatherDay>>
        get() = _actualWeatherWeekLiveData

    private val expectedListWeatherWeek = mutableListOf(
        GetData.getExpectedWeatherDay()
    )
    private val _expectedWeatherWeekLiveData = MutableLiveData<List<WeatherDay>>()
    private val expectedWeatherWeekLiveData: LiveData<List<WeatherDay>>
        get() = _expectedWeatherWeekLiveData

    /**
     * Проверяем не исказились ли данные из WeatherRepository, проходя через GetWeatherWeekUseCase.
     */

    @Test
    fun `getWeatherWeekUseCase should return the same data as in repository`() {
        //arrange
        _actualWeatherWeekLiveData.value = actualListWeatherWeek
        _expectedWeatherWeekLiveData.value = expectedListWeatherWeek
        Mockito.`when`(weatherRepositoryTest.getWeatherWeek()).thenReturn(actualWeatherWeekLiveData)

        //act
        val expected = expectedWeatherWeekLiveData
        val actual = GetWeatherWeekUseCase(repository = weatherRepositoryTest).invoke()

        //assert
        Assertions.assertEquals(expected.value, actual.value)
        Mockito.verify(weatherRepositoryTest, Mockito.times(1)).getWeatherWeek()
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