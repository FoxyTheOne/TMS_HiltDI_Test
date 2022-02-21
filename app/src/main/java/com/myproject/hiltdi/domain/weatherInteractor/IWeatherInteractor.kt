package com.myproject.hiltdi.domain.weatherInteractor

import androidx.lifecycle.LiveData
import com.myproject.hiltdi.model.presentation.WeatherResultPresentation

interface IWeatherInteractor {
    // Подписаться на репозиторий и получать оттуда ПРЕОБРАЗОВАННЫЕ в Presentation обновления
    fun subscribeOnWeather(): LiveData<List<WeatherResultPresentation>>
    // Просто достать погоду с сервера и сохранить в локальную базу данных (обратиться к репозиторию)
    suspend fun fetchWeather()
}