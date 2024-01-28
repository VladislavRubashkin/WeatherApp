package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeekViewModel::class.java)) {
            return WeekViewModel() as T
        }
        if (modelClass.isAssignableFrom(DayViewModel::class.java)) {
            return DayViewModel() as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}