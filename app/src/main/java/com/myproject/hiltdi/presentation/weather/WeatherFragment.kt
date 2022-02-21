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

        // ПОСЛЕДОВАТЕЛЬНОСТЬ: В первую очередь, мы подписываемся на локальную базу данных, чтобы получать из ViewModel ПРЕОБРАЗОВАННЫЕ в Presentation обновления
        subscribeOnLiveData()
        // ПОСЛЕДОВАТЕЛЬНОСТЬ: Затем достаём данные из локальной базы данных (локальная аза данных обновится и по подписке нам прилетят новые данные).
        // Для этого из View Model мы делаем запрос в корутине в Interactor
        viewModel.fetchWeather()
    }

    private fun subscribeOnLiveData() {
        // В подписке на LiveData мы заполняем наш RecyclerView Presentation погодой
        viewModel.weatherLiveData.observe(this, { weathers ->
            weatherRecyclerAdapter.addAllWeathers(weathers = weathers)
        })
    }
}