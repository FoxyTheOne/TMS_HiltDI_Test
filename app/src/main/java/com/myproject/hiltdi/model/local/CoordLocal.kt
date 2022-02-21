package com.myproject.hiltdi.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myproject.hiltdi.model.remote.Coord

@Entity
data class CoordLocal(
    @PrimaryKey
    @ColumnInfo(name = "coord_weather_result_id") val coordWeatherResultId: Int,
    @ColumnInfo( name = "lon") val lon: Double,
    @ColumnInfo( name = "lat") val lat: Double
) {
    companion object {
        fun fromRemoteToLocal(remote: Coord, coordWeatherResultId: Int): CoordLocal {

            return CoordLocal(
                lon = remote.lon,
                lat = remote.lat,
                coordWeatherResultId = coordWeatherResultId
            )

        }
    }
}