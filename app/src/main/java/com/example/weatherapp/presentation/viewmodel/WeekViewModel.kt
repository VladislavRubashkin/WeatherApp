package com.example.weatherapp.presentation.viewmodel

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecases.GetWeatherWeekUseCase
import com.example.weatherapp.domain.usecases.LoadWeatherUseCase
import com.example.weatherapp.presentation.utils.DialogManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeekViewModel @Inject constructor(
    private val getWeatherWeekUseCase: GetWeatherWeekUseCase,
    private val loadWeatherUseCase: LoadWeatherUseCase
) : ViewModel() {

    val weekWeather = getWeatherWeekUseCase()

    private val _checkGps = MutableLiveData<Boolean>()
    val checkGps: LiveData<Boolean>
        get() = _checkGps

    fun checkLocation(context: Context) {
        if (isLocationEnabled(context)) {
            _checkGps.value = true
        } else {
            _checkGps.value = false
            DialogManager.locationSettingsDialog(context, object : DialogManager.LocationListener {
                override fun onClick() {
                    _checkGps.value = true
                    startActivity(context, Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), null)
                }
            })
        }
    }

    // Проверка, включен ли GPS
    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun requestWeatherData(city: String) {
        viewModelScope.launch {
            loadWeatherUseCase(city)
        }
    }
}