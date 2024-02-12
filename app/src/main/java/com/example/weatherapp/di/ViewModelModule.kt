package com.example.weatherapp.di

import androidx.lifecycle.ViewModel
import com.example.weatherapp.presentation.viewmodel.DayViewModel
import com.example.weatherapp.presentation.viewmodel.WeekViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DayViewModel::class)
    fun bindDayViewModel(dayViewModel: DayViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeekViewModel::class)
    fun bindWeekViewModule(weekViewModel: WeekViewModel): ViewModel
}