package com.example.weatherapp.presentation

import android.app.Application
import com.example.weatherapp.di.DaggerApplicationComponent

class WeatherApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}