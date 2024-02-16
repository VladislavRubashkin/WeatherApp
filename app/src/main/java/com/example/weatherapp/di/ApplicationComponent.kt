package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.presentation.screens.DayFragment
import com.example.weatherapp.presentation.screens.WeekFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(dayFragment: DayFragment)

    fun inject(weekFragment: WeekFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}