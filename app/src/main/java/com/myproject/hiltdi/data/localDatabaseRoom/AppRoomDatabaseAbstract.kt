package com.myproject.hiltdi.data.localDatabaseRoom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myproject.hiltdi.model.local.*

@Database(
    entities = [WeatherWrapperLocal::class, CloudsLocal::class, CoordLocal::class, MainLocal::class, SysLocal::class, WeatherLocal::class, WindLocal::class],
    version = 1,
    exportSchema = false
)
abstract class AppRoomDatabaseAbstract : RoomDatabase() {
    abstract fun getWeatherDAO(): IWeatherDAO
}