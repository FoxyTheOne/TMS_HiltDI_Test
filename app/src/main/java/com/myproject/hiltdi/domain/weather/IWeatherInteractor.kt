package com.myproject.hiltdi.domain.weather

import com.myproject.hiltdi.model.presentation.WeatherPresentation

interface IWeatherInteractor {
    suspend fun getWeather(): List<WeatherPresentation>
}