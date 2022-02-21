package com.myproject.hiltdi.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myproject.hiltdi.model.remote.Weather

@Entity
data class WeatherLocal(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "main") val main: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "weather_weather_result_id") val weatherWeatherResultId: Int
) {
    companion object {
        fun fromRemoteToLocal(remote: Weather, weatherWeatherResultId: Int): WeatherLocal {

            return WeatherLocal(
                id = remote.id,
                main = remote.main,
                description = remote.description,
                icon = remote.icon,
                weatherWeatherResultId = weatherWeatherResultId
            )

        }
    }
}