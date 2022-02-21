package com.myproject.hiltdi.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myproject.hiltdi.model.remote.Sys

@Entity
data class SysLocal(
    @PrimaryKey
    @ColumnInfo(name = "sys_weather_result_id") val sysWeatherResultId: Int,
    @ColumnInfo(name = "type") val type: Int,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "sunrise") val sunrise: Int,
    @ColumnInfo(name = "sunset") val sunset: Int
) {
    companion object {
        fun fromRemoteToLocal(remote: Sys, sysWeatherResultId: Int): SysLocal {

            return SysLocal(
                type = remote.type,
                id = remote.id,
                country = remote.country,
                sunrise = remote.sunrise,
                sunset = remote.sunset,
                sysWeatherResultId = sysWeatherResultId
            )

        }
    }
}