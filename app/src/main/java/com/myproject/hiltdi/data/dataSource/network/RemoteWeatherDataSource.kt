package com.myproject.hiltdi.data.dataSource.network

import com.myproject.hiltdi.data.networkService.WeatherService
import com.myproject.hiltdi.model.remote.WeatherResultRemote
import javax.inject.Inject

/**
 * Класс для обращения за данными на сервер.
 * Будем использовать наш WeatherService для end point запроса => @Inject объект этого класса в конструктор
 */
class RemoteWeatherDataSource @Inject constructor(private val weatherService: WeatherService):
    IRemoteWeatherDataSource {
    // Для реализации простого запроса, составим список городов в companion object
    companion object {
        private const val MINSK = "Minsk"
        private const val LVIV = "Lviv"
        private const val NEW_YORK = "New York"
    }

    override suspend fun getWeatherList(): List<WeatherResultRemote> {
        val cityList = listOf(MINSK, LVIV, NEW_YORK)

        // По умолчанию в методе - город Минск. Мы будем передавать другие, поэтому в метод добавим параметр ciy
        val weatherRemoteList = mutableListOf<WeatherResultRemote>()

        cityList.forEach { city ->
            weatherRemoteList.add(weatherService.getWeather(city = city))
        }

        return weatherRemoteList
    }
}