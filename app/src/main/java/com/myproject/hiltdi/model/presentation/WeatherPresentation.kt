package com.myproject.hiltdi.model.presentation

import com.myproject.hiltdi.model.remote.WeatherRemote

data class WeatherPresentation(val city: String, val temperature: Double, val humidity: Int, val icon: String) {

    companion object {
        fun fromRemote(weatherRemote: WeatherRemote): WeatherPresentation = WeatherPresentation(
            city = weatherRemote.name,
            temperature = weatherRemote.main.temp,
            humidity = weatherRemote.main.humidity,
            icon = weatherRemote.weather.first().icon
        )
    }

}