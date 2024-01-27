package com.example.weatherapp.presentation.screens

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeekBinding
import com.example.weatherapp.presentation.adapters.WeekAdapter
import com.example.weatherapp.presentation.viewmodel.WeekViewModel

class WeekFragment : Fragment() {

    private var _binding: FragmentWeekBinding? = null
    private val binding: FragmentWeekBinding
        get() = _binding ?: throw RuntimeException()

    private val weekViewModel by lazy {
        ViewModelProvider(this)[WeekViewModel::class.java]
    }

    private lateinit var weekAdapter: WeekAdapter

    private val locationManager by lazy {
        requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
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
        checkPermission()
        init()
        observe()
        launchDayFragment()
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

    private fun checkPermission() {
        val permissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val permissionGranted2 = ActivityCompat.checkSelfPermission(
            requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (permissionGranted && permissionGranted2) {
            Toast.makeText(requireContext(), "Разрешение получено1", Toast.LENGTH_SHORT).show()
            requestLocation()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            REQUEST_CODE_PERMISSION
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSION && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Разрешение получено2", Toast.LENGTH_SHORT).show()
                requestLocation()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Не возможно продолжать без данных разрешений",
                    Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun requestLocation() {
        try {
//                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        } catch (e: SecurityException) {
            Log.d("TAG", e.message.toString())
        }
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