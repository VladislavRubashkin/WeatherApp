package com.example.weatherapp.presentation.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
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
import com.example.weatherapp.data.api.ApiFactory
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.databinding.FragmentWeekBinding
import com.example.weatherapp.presentation.adapters.WeekAdapter
import com.example.weatherapp.presentation.utils.Constants
import com.example.weatherapp.presentation.viewmodel.ViewModelFactory
import com.example.weatherapp.presentation.viewmodel.WeekViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeekFragment : Fragment() {

    private var _binding: FragmentWeekBinding? = null
    private val binding: FragmentWeekBinding
        get() = _binding ?: throw RuntimeException("WeekFragment == null")

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

        initRecyclerView()
        launchDayFragment()
        observe()
//        getLocation()

    }

    override fun onResume() {
        super.onResume()
        weekViewModel.checkLocation(requireContext())
    }

    private fun initRecyclerView() {
        weekAdapter = WeekAdapter()
        binding.rvWeekAdapter.adapter = weekAdapter
        binding.rvWeekAdapter.layoutManager = LinearLayoutManager(activity)
    }

    private fun launchDayFragment() {
        weekAdapter.dayClickListener = {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_container, DayFragment.newInstance(it))
                .addToBackStack(Constants.DAY_FRAGMENT_NAME)
                .commit()
        }
    }

    private fun observe() {
        weekViewModel.weekWeather.observe(viewLifecycleOwner) {
            weekAdapter.submitList(it)
        }
        weekViewModel.checkLocation(requireContext())
        weekViewModel.checkGps.observe(viewLifecycleOwner) {
            if (it) {
                getLocation()
            }
        }
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val task = fusedLocationProviderClient.getCurrentLocation(
                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                CancellationTokenSource().token
            )
            task.addOnCompleteListener {
                // TODO метод принимает широту и долготу и Обращается к серверу(возможно???) ?вынести во viewModel?
                Log.d("Tag", "Широта: ${it.result.latitude} Долгота:${it.result.longitude}")
                weekViewModel.requestWeatherData("${it.result.latitude},${it.result.longitude}")
            }
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            Constants.REQUEST_CODE_PERMISSION
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == Constants.REQUEST_CODE_PERMISSION && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(requireContext(), "Разрешение получено2", Toast.LENGTH_SHORT).show()
                getLocation()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Не возможно продолжать без данных разрешений",
                    Toast.LENGTH_LONG
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

        fun newInstance() = WeekFragment()
    }
}