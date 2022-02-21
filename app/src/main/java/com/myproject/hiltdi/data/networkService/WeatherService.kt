package com.myproject.hiltdi.data.networkService

import com.myproject.hiltdi.BuildConfig
import com.myproject.hiltdi.model.remote.WeatherResultRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * RETROFIT -> 1. Составляем end point нашего запроса
 * Получаем погоду в Минске
 */
interface WeatherService {
    companion object {
        private const val BASE_PATH = "data/2.5" // base path
        private const val WEATHER_PATH = "weather" // path
        private const val KEY_CITY = "Minsk"
        private const val KEY_UNITS = "metric"
        private const val KEY_API_KEY = BuildConfig.OPEN_WEATHER_MAP_API_KEY
    }

    @GET("$BASE_PATH/{weather}") // base path
    suspend fun getWeather(
        @Path("weather") weather: String = WEATHER_PATH, // path
        @Query("q") city: String = KEY_CITY,
        @Query("units") units: String = KEY_UNITS,
        @Query("appid") appid: String = KEY_API_KEY,
    ): WeatherResultRemote
}