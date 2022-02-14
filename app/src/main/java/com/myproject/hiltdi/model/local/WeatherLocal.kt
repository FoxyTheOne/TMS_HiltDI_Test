package com.myproject.hiltdi.model.local

import com.myproject.hiltdi.model.IWeather

data class WeatherLocal(
    val city: String,
    val temperature: Double,
    val humidity: Int,
    val icon: String
) : IWeather