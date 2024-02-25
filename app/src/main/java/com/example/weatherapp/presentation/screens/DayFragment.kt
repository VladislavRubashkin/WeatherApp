package com.example.weatherapp.presentation.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.FragmentDayBinding
import com.example.weatherapp.domain.entity.WeatherDay
import com.example.weatherapp.presentation.WeatherApplication
import com.example.weatherapp.presentation.utils.Constants
import com.example.weatherapp.presentation.viewmodel.DayViewModel
import com.example.weatherapp.presentation.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DayFragment : Fragment() {

    private var _binding: FragmentDayBinding? = null
    private val binding: FragmentDayBinding
        get() = _binding ?: throw RuntimeException("DayFragment binding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DayViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as WeatherApplication).component
    }

    private var dayId: Int = 0

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

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
        backToWeekFragment()
    }

    private fun backToWeekFragment() {
        binding.fabWeek.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun launchDayFragment() {
        viewModel.getWeatherDay(dayId).observe(viewLifecycleOwner) {
            binding.tvData.text = it.date
            val maxMinTemp = "${it.maxTemp} C° / ${it.minTemp} C°"
            binding.tvMaxMinTemp.text = maxMinTemp
            binding.tvCity.text = it.city
            binding.tvCondition.text = it.condition
            binding.tvCurrentTemp.text = it.currentTemp
//            temperatureByHour(it)
            Picasso.get().load("https:" + it.imageUrl).into(binding.imWeather)
        }
    }

    private fun temperatureByHour(weatherDay: WeatherDay) {
        var averageTempNight = 0.0f
        var averageTempMorning = 0.0f
        var averageTempDay = 0.0f
        var averageTempEvening = 0.0f
        for (i in 0 until weatherDay.hours.size) {
            when(i) {
                in 0..5 -> averageTempNight += weatherDay.hours[i].temp
                in 6..11 -> averageTempMorning += weatherDay.hours[i].temp
                in 12..17 -> averageTempDay += weatherDay.hours[i].temp
                in 18..23 -> averageTempEvening += weatherDay.hours[i].temp
            }
        }
        averageTempNight /= 6
        averageTempMorning /= 6
        averageTempDay /= 6
        averageTempEvening /= 6
        binding.tvTempNight.text = averageTempNight.toString()
        binding.tvTempMorning.text = averageTempMorning.toString()
        binding.tvTempDay.text = averageTempDay.toString()
        binding.tvTempEvening.text = averageTempEvening.toString()
    }

    private fun parseArgs() {
        val args = requireArguments()
        if (!args.containsKey(Constants.DAY_FRAGMENT_ID)) {
            throw RuntimeException("not found args $args")
        }
        dayId = args.getInt(Constants.DAY_FRAGMENT_ID, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(dayId: Int): DayFragment {
            return DayFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.DAY_FRAGMENT_ID, dayId)
                }
            }
        }
    }
}