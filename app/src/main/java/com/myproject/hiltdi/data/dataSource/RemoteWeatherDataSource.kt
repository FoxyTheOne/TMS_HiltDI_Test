package com.myproject.hiltdi.data.dataSource

import com.myproject.hiltdi.data.network.serviceWeather.WeatherService
import com.myproject.hiltdi.domain.iDataSource.IWeatherDataSource
import com.myproject.hiltdi.model.IWeather
import javax.inject.Inject

/**
 * Класс для обращения за данными на сервер.
 * Будем использовать наш WeatherService для end point запроса => @Inject объект этого класса в конструктор
 */
class RemoteWeatherDataSource @Inject constructor(private val weatherService: WeatherService): IWeatherDataSource {
    override suspend fun getWeather(city: String): IWeather {
        // По умолчанию в методе - город Минск. Мы будем передавать другие, поэтому в метод добавим параметр ciy
        return weatherService.getWeather(city = city)
    }
}