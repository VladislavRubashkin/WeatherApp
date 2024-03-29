package com.example.weatherapp.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSplashBinding
import com.example.weatherapp.presentation.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding
        get() = _binding ?: throw RuntimeException("SplashFragment == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(Constants.START_DELAY)
            launchDayWeekFragment()
        }
    }

    private fun launchDayWeekFragment() {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .replace(R.id.activity_container, WeekFragment.newInstance())
            .addToBackStack(Constants.WEEK_FRAGMENT_NAME)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = SplashFragment()
    }
}