package com.myproject.hiltdi.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.hiltdi.domain.weatherInteractor.IWeatherInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private var weatherInteractor: IWeatherInteractor): ViewModel() {
    // Чтобы обратиться к Interactor и вызвать его метод, нам нужен его объект в view model. Передадим его через конструктор

    companion object {
        private const val TAG = "WeatherViewModel"
    }

    // ПОСЛЕДОВАТЕЛЬНОСТЬ: В первую очередь, мы подписываемся на локальную базу данных, чтобы получать из Interactor ПРЕОБРАЗОВАННЫЕ в Presentation обновления
    val weatherLiveData = weatherInteractor.subscribeOnWeather()


    // ПОСЛЕДОВАТЕЛЬНОСТЬ: Затем достаём данные из локальной базы данных (локальная аза данных обновится и по подписке нам прилетят новые данные).
    // Для этого из View Model мы делаем запрос в корутине в Interactor
    fun fetchWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            weatherInteractor.fetchWeather()
        }
    }
}