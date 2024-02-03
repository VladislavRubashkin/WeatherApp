package com.example.weatherapp.presentation.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityContainerBinding
import com.example.weatherapp.presentation.utils.Constants

class ContainerActivity : AppCompatActivity() {

    private val binding: ActivityContainerBinding by lazy {
        ActivityContainerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        launchSplashFragment()
    }

    private fun launchSplashFragment() {
        // TODO разобраться с помещением и удалением фрагментов в backStack - чтобы из WeekFragment выходить
        //  из приложения
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, SplashFragment.newInstance())
//            .addToBackStack(Constants.SPLASH_FRAGMENT_NAME)
            .commit()
    }
}