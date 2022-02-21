package com.myproject.hiltdi.model.presentation

import com.myproject.hiltdi.model.local.WeatherEntityResultLocal

data class WeatherResultPresentation(val city: String, val temperature: Double, val humidity: Int, val icon: String) {

    companion object {
        fun fromLocalToPresentation(weatherEntityResultLocal: WeatherEntityResultLocal) : WeatherResultPresentation = WeatherResultPresentation(
            city = weatherEntityResultLocal.weatherWrapperLocal.name,
            temperature = weatherEntityResultLocal.mainLocal?.temp ?: 0.0,
            humidity = weatherEntityResultLocal.mainLocal?.humidity ?: 0,
            icon = weatherEntityResultLocal.weatherLocal?.firstOrNull()?.icon ?: ""
        )
    }

}