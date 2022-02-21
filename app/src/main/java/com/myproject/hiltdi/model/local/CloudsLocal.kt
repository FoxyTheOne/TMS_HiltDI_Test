package com.myproject.hiltdi.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myproject.hiltdi.model.remote.Clouds

@Entity
data class CloudsLocal(
    @PrimaryKey
    @ColumnInfo(name = "clouds_weather_result_id") val cloudsWeatherResultId: Int,
    @ColumnInfo(name = "all") val all: Int
) {
    companion object {
        fun fromRemoteToLocal(remote: Clouds, cloudsWeatherResultId: Int): CloudsLocal {

            return CloudsLocal(
                all = remote.all,
                cloudsWeatherResultId = cloudsWeatherResultId
            )

        }
    }
}


