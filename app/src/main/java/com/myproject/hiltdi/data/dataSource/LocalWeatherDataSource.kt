package com.myproject.hiltdi.data.dataSource

import com.myproject.hiltdi.domain.iDataSource.IWeatherDataSource
import com.myproject.hiltdi.model.IWeather
import com.myproject.hiltdi.model.local.WeatherLocal
import javax.inject.Inject

/**
 * LocalWeatherDataSource будет доставать данные, либо сохранять их в локальную базу данных Room
 */
class LocalWeatherDataSource @Inject constructor(): IWeatherDataSource {
    override suspend fun getWeather(city: String): IWeather {
        return WeatherLocal("", 0.0, 0, "")
    }
}