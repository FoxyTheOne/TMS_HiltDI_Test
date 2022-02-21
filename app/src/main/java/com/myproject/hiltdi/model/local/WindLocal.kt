package com.myproject.hiltdi.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myproject.hiltdi.model.remote.Wind

@Entity
data class WindLocal(
    @PrimaryKey
    @ColumnInfo(name = "wind_weather_result_id") val windWeatherResultId: Int,
    @ColumnInfo( name = "speed") val speed: Double,
    @ColumnInfo( name = "deg") val deg: Int,
    @ColumnInfo( name = "gust") val gust: Double
) {
    companion object {
        fun fromRemoteToLocal(remote: Wind, windWeatherResultId: Int): WindLocal {

            return WindLocal(
                speed = remote.speed,
                deg = remote.deg,
                gust = remote.gust,
                windWeatherResultId = windWeatherResultId
            )

        }
    }
}