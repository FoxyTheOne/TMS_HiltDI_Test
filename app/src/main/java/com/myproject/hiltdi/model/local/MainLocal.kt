package com.myproject.hiltdi.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myproject.hiltdi.model.remote.Main

@Entity
data class MainLocal(
    @PrimaryKey
    @ColumnInfo(name = "main_weather_result_id") val mainWeatherResultId: Int,
    @ColumnInfo(name = "temp") val temp: Double,
    @ColumnInfo(name = "feels_like") val feels_like: Double,
    @ColumnInfo(name = "temp_min") val temp_min: String,
    @ColumnInfo(name = "temp_max") val temp_max: String,
    @ColumnInfo(name = "pressure") val pressure: Int,
    @ColumnInfo(name = "humidity") val humidity: Int,
    @ColumnInfo(name = "sea_level") val sea_level: Int,
    @ColumnInfo(name = "grnd_level") val grnd_level: Int
) {
    companion object {
        fun fromRemoteToLocal(remote: Main, mainWeatherResultId: Int): MainLocal {

            return MainLocal(
                temp = remote.temp,
                feels_like = remote.feels_like,
                temp_min = remote.temp_min,
                temp_max = remote.temp_max,
                pressure = remote.pressure,
                humidity = remote.humidity,
                sea_level = remote.sea_level,
                grnd_level = remote.grnd_level,
                mainWeatherResultId = mainWeatherResultId
            )

        }
    }
}