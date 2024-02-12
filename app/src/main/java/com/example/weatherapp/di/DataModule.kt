package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.data.api.ApiFactory
import com.example.weatherapp.data.api.ApiService
import com.example.weatherapp.data.database.AppDatabase
import com.example.weatherapp.data.database.WeatherDao
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideWeatherDao(
            application: Application
        ): WeatherDao {
            return AppDatabase.getInstance(application).weatherDao()
        }

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}