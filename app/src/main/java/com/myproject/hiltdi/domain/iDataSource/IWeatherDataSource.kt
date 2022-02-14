package com.myproject.hiltdi.domain.iDataSource

import com.myproject.hiltdi.model.IWeather

/**
 * Интерфесы используются в domain, поэтому хранятся в папке на уровень ниже
 */
interface IWeatherDataSource {
    suspend fun getWeather(city: String = ""): IWeather
}