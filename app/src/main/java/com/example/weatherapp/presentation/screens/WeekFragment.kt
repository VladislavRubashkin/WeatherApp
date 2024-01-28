package com.example.weatherapp.presentation.screens

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeekBinding
import com.example.weatherapp.presentation.adapters.WeekAdapter
import com.example.weatherapp.presentation.utils.DialogManager
import com.example.weatherapp.presentation.viewmodel.ViewModelFactory
import com.example.weatherapp.presentation.viewmodel.WeekViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class WeekFragment : Fragment() {

    private var _binding: FragmentWeekBinding? = null
    private val binding: FragmentWeekBinding
        get() = _binding ?: throw RuntimeException()

    private val viewModelFactory by lazy {
        ViewModelFactory()
    }

    private val weekViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[WeekViewModel::class.java]
    }

    private lateinit var weekAdapter: WeekAdapter

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
        launchDayFragment()
        getLocation()
    }

    override fun onResume() {
        super.onResume()
        checkLocation()
    }

    private fun init() {
        weekAdapter = WeekAdapter()
        binding.rvWeekAdapter.adapter = weekAdapter
        binding.rvWeekAdapter.layoutManager = LinearLayoutManager(activity)
    }

    private fun observe() {
        weekViewModel.weekWeather.observe(viewLifecycleOwner) {
            weekAdapter.submitList(it)
        }
    }

    private fun launchDayFragment() {
        weekAdapter.dayClickListener = {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_container, DayFragment.newInstance(it))
                .addToBackStack(DayFragment.FRAGMENT_NAME)
                .commit()
        }
    }

    // Запуск диалога по включению GPS, если он выключен
    private fun checkLocation() {
        if (isLocationEnabled()) {
            getLocation()
        } else {
            DialogManager.locationSettingsDialog(requireContext(), object : DialogManager.Listener {
                override fun onClick() {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }
            })
        }
    }

    // Проверка, включен ли GPS
    private fun isLocationEnabled(): Boolean {
        val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val task = fusedLocationProviderClient.getCurrentLocation(
                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                CancellationTokenSource().token
            )
            task.addOnCompleteListener {
                Toast.makeText(
                    requireContext(),
                    "Широта: ${it.result.latitude} Долгота:${it.result.longitude}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("Tag", "Широта: ${it.result.latitude} Долгота:${it.result.longitude}")
            }
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            REQUEST_CODE_PERMISSION
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSION && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(requireContext(), "Разрешение получено2", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(
                    requireContext(),
                    "Не возможно продолжать без данных разрешений",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_NAME = "WeekFragment"
        private const val REQUEST_CODE_PERMISSION = 100

        fun newInstance() = WeekFragment()
    }
}