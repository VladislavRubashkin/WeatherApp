package com.example.weatherapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeekViewModel::class.java)) {
            return WeekViewModel(application) as T
        }
        if (modelClass.isAssignableFrom(DayViewModel::class.java)) {
            return DayViewModel(application) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}