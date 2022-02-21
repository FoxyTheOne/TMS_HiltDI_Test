package com.myproject.hiltdi.data.dataSource.local

import androidx.lifecycle.LiveData
import com.myproject.hiltdi.data.localDatabaseRoom.IWeatherDAO
import com.myproject.hiltdi.model.local.*
import javax.inject.Inject

/**
 * LocalWeatherDataSource будет доставать данные, либо сохранять их в локальную базу данных Room
 */
class LocalWeatherDataSource @Inject constructor(
    val weatherDAO: IWeatherDAO
): ILocalWeatherDataSource {
    // Подписаться на локальную базу данных и получать оттуда обновления
    override fun subscribeOnWeather(): LiveData<List<WeatherEntityResultLocal>> = weatherDAO.getWeatherLocalList()

    override suspend fun insertALLCloudsLocal(cloudsLocalList: List<CloudsLocal>) = weatherDAO.insertALLCloudsLocal(cloudsLocalList)
    override suspend fun insertALLCoordLocal(coordLocalList: List<CoordLocal>) = weatherDAO.insertALLCoordLocal(coordLocalList)
    override suspend fun insertALLMainLocal(mainLocalList: List<MainLocal>) = weatherDAO.insertALLMainLocal(mainLocalList)
    override suspend fun insertALLSysLocal(sysLocalList: List<SysLocal>) = weatherDAO.insertALLSysLocal(sysLocalList)
    override suspend fun insertALLWeatherLocal(weatherLocalList: List<WeatherLocal>) = weatherDAO.insertALLWeatherLocal(weatherLocalList)
    override suspend fun insertALLWeatherWrappersLocal(weatherWrapperLocalList: List<WeatherWrapperLocal>) = weatherDAO.insertALLWeatherWrappersLocal(weatherWrapperLocalList)
    override suspend fun insertALLWindLocal(windLocalList: List<WindLocal>) = weatherDAO.insertALLWindLocal(windLocalList)

}