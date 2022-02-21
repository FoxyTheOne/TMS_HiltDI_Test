package com.myproject.hiltdi.domain.iWeatherRepository

import androidx.lifecycle.LiveData
import com.myproject.hiltdi.model.presentation.WeatherResultPresentation

interface IWeatherRepository {
    // Подписаться на локальную базу данных и получать оттуда ПРЕОБРАЗОВАННЫЕ в Presentation обновления
    fun subscribeOnWeather(): LiveData<List<WeatherResultPresentation>>
    // Просто достать погоду с сервера и сохранить в локальную базу данных
    suspend fun fetchWeather()
}