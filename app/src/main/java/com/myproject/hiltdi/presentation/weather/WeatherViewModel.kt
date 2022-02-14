package com.myproject.hiltdi.presentation.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.hiltdi.domain.weather.IWeatherInteractor
import com.myproject.hiltdi.model.presentation.WeatherPresentation
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

    val weatherLiveData = MutableLiveData<List<WeatherPresentation>>()

    // 2. Из View Model мы делаем запрос в корутине в Interactor и в ответ получаем готовые к обработке данные, которые сразу передаём в LiveData
    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            weatherLiveData.postValue(weatherInteractor.getWeather())
        }
    }
}