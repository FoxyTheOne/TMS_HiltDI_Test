package com.myproject.hiltdi.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myproject.hiltdi.model.remote.WeatherResultRemote

@Entity
data class WeatherWrapperLocal(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "base") val base: String,
    @ColumnInfo(name = "visibility") val visibility: Int,
    @ColumnInfo(name = "dt") val dt: Int,
    @ColumnInfo(name = "timezone") val timezone: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "cod") val cod: Int
) {
    companion object {
        fun fromRemoteToLocal(remote: WeatherResultRemote): WeatherWrapperLocal {
            val weatherLocalId = remote.id

            return WeatherWrapperLocal(
                id = weatherLocalId,
                base = remote.base,
                visibility = remote.visibility,
                dt = remote.dt,
                timezone = remote.timezone,
                name = remote.name,
                cod = remote.cod
            )

        }
    }
}