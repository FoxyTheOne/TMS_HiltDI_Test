package com.myproject.hiltdi.data.dataSource.local

import androidx.lifecycle.LiveData
import com.myproject.hiltdi.model.local.*

interface ILocalWeatherDataSource {
    fun subscribeOnWeather(): LiveData<List<WeatherEntityResultLocal>> // Подписаться на локальную базу данных и получать оттуда обновления

    suspend fun insertALLCloudsLocal(cloudsLocalList: List<CloudsLocal>)
    suspend fun insertALLCoordLocal(coordLocalList: List<CoordLocal>)
    suspend fun insertALLMainLocal(mainLocalList: List<MainLocal>)
    suspend fun insertALLSysLocal(sysLocalList: List<SysLocal>)
    suspend fun insertALLWeatherLocal(weatherLocalList: List<WeatherLocal>)
    suspend fun insertALLWeatherWrappersLocal(weatherWrapperLocalList: List<WeatherWrapperLocal>)
    suspend fun insertALLWindLocal(windLocalList: List<WindLocal>)
}