package com.myproject.hiltdi.model.local

import androidx.room.Embedded
import androidx.room.Relation
import com.myproject.hiltdi.model.IWeather

class WeatherEntityResultLocal(
    @Embedded
    val weatherWrapperLocal: WeatherWrapperLocal,

    @Relation(
        parentColumn = "id", // Каким ключомобозначается в parent (WeatherWrapperLocal) необходимое на id, которое мы передаём в CloudsLocal
        entityColumn = "clouds_weather_result_id" // Куда этот id мы сохранили внутри CloudsLocal
    )
    val cloudsLocal: CloudsLocal?,

    @Relation(
        parentColumn = "id",
        entityColumn = "coord_weather_result_id"
    )
    val coordLocal: CoordLocal?,

    @Relation(
        parentColumn = "id",
        entityColumn = "main_weather_result_id"
    )
    val mainLocal: MainLocal?,

    @Relation(
        parentColumn = "id",
        entityColumn = "sys_weather_result_id"
    )
    val sysLocal: SysLocal?,

    @Relation(
        parentColumn = "id",
        entityColumn = "weather_weather_result_id"
    )
    val weatherLocal: List<WeatherLocal>?,

    @Relation(
        parentColumn = "id",
        entityColumn = "wind_weather_result_id"
    )
    val windLocal: WindLocal?,
) : IWeather