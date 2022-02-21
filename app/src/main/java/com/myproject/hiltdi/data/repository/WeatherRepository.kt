package com.myproject.hiltdi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.myproject.hiltdi.data.dataSource.local.ILocalWeatherDataSource
import com.myproject.hiltdi.data.dataSource.network.IRemoteWeatherDataSource
import com.myproject.hiltdi.domain.iWeatherRepository.IWeatherRepository
import com.myproject.hiltdi.model.local.*
import com.myproject.hiltdi.model.presentation.WeatherResultPresentation
import com.myproject.hiltdi.model.remote.WeatherResultRemote
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remoteWeatherDataSource: IRemoteWeatherDataSource,
    private val localWeatherDataSource: ILocalWeatherDataSource
) : IWeatherRepository {

    // Подписаться на локальную базу данных.
    // Перехватить всё, что прилетает из базы данных и превратить в другую форму модели, возвращая Presentation
    override fun subscribeOnWeather(): LiveData<List<WeatherResultPresentation>> {
        // Для того, чтобы сделать перехват, пользуемся MediatorLiveData
        val weatherMediatorLiveData = MediatorLiveData<List<WeatherResultPresentation>>()

        // Будем слушать все ивенты, которые прилетают из подписки на базу данных и обрабатывать
        weatherMediatorLiveData.addSource(localWeatherDataSource.subscribeOnWeather()){ weatherEntityResultLocalList ->
            val weatherResultPresentationList = mutableListOf<WeatherResultPresentation>()

            weatherEntityResultLocalList.forEach { weatherEntityResultLocal ->
                weatherResultPresentationList.add(WeatherResultPresentation.fromLocalToPresentation(weatherEntityResultLocal))
            }

            weatherMediatorLiveData.postValue(weatherResultPresentationList)
        }
        return weatherMediatorLiveData
    }

    override suspend fun fetchWeather() {
        // Сначала сделаем запрос на сервер
        val weatherResultRemoteList = remoteWeatherDataSource.getWeatherList()

        // Создадим массивы для заполнения (при преобразовании WeatherRemote -> WeatherLocal)
        val cloudsLocalList = mutableListOf<CloudsLocal>()
        val coordLocalList = mutableListOf<CoordLocal>()
        val mainLocalList = mutableListOf<MainLocal>()
        val sysLocalList = mutableListOf<SysLocal>()
        val weatherLocalList = mutableListOf<WeatherLocal>()
        val weatherWrapperLocalList = mutableListOf<WeatherWrapperLocal>()
        val windLocalList = mutableListOf<WindLocal>()

        // И заполним их
        weatherResultRemoteList.forEach { weatherResultRemote ->
            if (weatherResultRemote is WeatherResultRemote) {
                val weatherLocalId = weatherResultRemote.id

                cloudsLocalList.add(CloudsLocal.fromRemoteToLocal(remote = weatherResultRemote.clouds, weatherLocalId))
                coordLocalList.add(CoordLocal.fromRemoteToLocal(remote = weatherResultRemote.coord, weatherLocalId))
                mainLocalList.add(MainLocal.fromRemoteToLocal(remote = weatherResultRemote.main, weatherLocalId))
                sysLocalList.add(SysLocal.fromRemoteToLocal(remote = weatherResultRemote.sys, weatherLocalId))

                weatherResultRemote.weather.forEach { weatherRemoteFromWeatherRemoteList ->
                    weatherLocalList.add(WeatherLocal.fromRemoteToLocal(remote = weatherRemoteFromWeatherRemoteList, weatherLocalId))
                }

                weatherWrapperLocalList.add(WeatherWrapperLocal.fromRemoteToLocal(weatherResultRemote))
                windLocalList.add(WindLocal.fromRemoteToLocal(remote = weatherResultRemote.wind, weatherLocalId))

            }
        }

        // Далее полученные WeatherLocal необходимо сохранить в Room
        localWeatherDataSource.insertALLCloudsLocal(cloudsLocalList)
        localWeatherDataSource.insertALLCoordLocal(coordLocalList)
        localWeatherDataSource.insertALLMainLocal(mainLocalList)
        localWeatherDataSource.insertALLSysLocal(sysLocalList)
        localWeatherDataSource.insertALLWeatherLocal(weatherLocalList)
        localWeatherDataSource.insertALLWeatherWrappersLocal(weatherWrapperLocalList)
        localWeatherDataSource.insertALLWindLocal(windLocalList)
    }

}