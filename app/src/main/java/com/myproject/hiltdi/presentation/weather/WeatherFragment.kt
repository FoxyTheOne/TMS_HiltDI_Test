package com.myproject.hiltdi.presentation.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.myproject.hiltdi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment: Fragment() {
    companion object {
        private const val TAG = "WeatherFragment"
    }

    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var weatherRecyclerView: RecyclerView
    private lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherRecyclerView = view.findViewById(R.id.recyclerView_weather)
        weatherRecyclerAdapter = WeatherRecyclerAdapter { weather ->
            println("Selected Weather: $weather")
        }
        weatherRecyclerView.adapter = weatherRecyclerAdapter

        // 1. Делаем запрос на получение погоды из фрагмента в view model и ждем результат, готовый для отображения
        viewModel.getWeather()

        subscribeOnLiveData()
    }

    private fun subscribeOnLiveData() {
        viewModel.weatherLiveData.observe(this, { weathers ->
            weatherRecyclerAdapter.addAllWeathers(weathers = weathers)
        })
    }
}