package com.example.weatherapp.presentation.screens

import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.FragmentDayBinding
import com.example.weatherapp.presentation.viewmodel.DayViewModel

class DayFragment : Fragment() {

    private var _binding: FragmentDayBinding? = null
    private val binding: FragmentDayBinding
        get() = _binding ?: throw RuntimeException("MainFragment == null")

    private val viewModel by lazy {
        ViewModelProvider(this)[DayViewModel::class.java]
    }

    private var dayId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchDayFragment()
    }

    private fun launchDayFragment() {
        viewModel.getWeatherDay(dayId)
        viewModel.weatherDay.observe(viewLifecycleOwner) {
            binding.tvData.text = it.date
            binding.tvMaxMinTemp.text = it.maxTemp + it.minTemp
            binding.tvCity.text = it.city
            binding.tvCondition.text = it.condition
            binding.tvCurrentTemp.text = it.currentTemp
        }
    }

    private fun parseArgs() {
        val args = requireArguments()
        if (!args.containsKey(FRAGMENT_ID)) {
            throw RuntimeException("not found args $args")
        }
        dayId = args.getInt(FRAGMENT_ID, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_NAME = "DayFragment"
        private const val FRAGMENT_ID = "dayFragmentId"

        fun newInstance(dayId: Int): DayFragment {
            return DayFragment().apply {
                arguments = Bundle().apply {
                    putInt(FRAGMENT_ID, dayId)
                }
            }
        }
    }
}