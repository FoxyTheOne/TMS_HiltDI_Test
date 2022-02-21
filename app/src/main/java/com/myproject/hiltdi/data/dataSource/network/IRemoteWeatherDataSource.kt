package com.myproject.hiltdi.data.dataSource.network

import com.myproject.hiltdi.model.remote.WeatherResultRemote

interface IRemoteWeatherDataSource {
    suspend fun getWeatherList(): List<WeatherResultRemote>
}