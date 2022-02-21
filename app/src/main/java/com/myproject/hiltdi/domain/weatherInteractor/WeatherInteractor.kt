package com.myproject.hiltdi.domain.weatherInteractor

import androidx.lifecycle.LiveData
import com.myproject.hiltdi.domain.iWeatherRepository.IWeatherRepository
import com.myproject.hiltdi.model.presentation.WeatherResultPresentation
import javax.inject.Inject

class WeatherInteractor @Inject constructor(
    private val weatherRepository: IWeatherRepository
): IWeatherInteractor {

    // Подписаться на репозиторий и получать оттуда ПРЕОБРАЗОВАННЫЕ в Presentation обновления
    override fun subscribeOnWeather(): LiveData<List<WeatherResultPresentation>> = weatherRepository.subscribeOnWeather()

    // Просто достать погоду с сервера и сохранить в локальную базу данных (обратиться к репозиторию)
    override suspend fun fetchWeather() = weatherRepository.fetchWeather()

}