package com.example.weatherapp.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityContainerBinding

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
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, SplashFragment.newInstance())
            .commit()
    }
}