package com.myproject.hiltdi.data.localDatabaseRoom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myproject.hiltdi.model.local.*

@Dao
interface IWeatherDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALLCloudsLocal(weatherList: List<CloudsLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALLCoordLocal(weatherList: List<CoordLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALLMainLocal(weatherList: List<MainLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALLSysLocal(weatherList: List<SysLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALLWeatherLocal(weatherList: List<WeatherLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALLWeatherWrappersLocal(weatherList: List<WeatherWrapperLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALLWindLocal(weatherList: List<WindLocal>)

    // Сформируем запрс на получение WeatherEntity в DAO
    // Мы получаем обертку WeatherEntity, но по факиу говоримб дай нам WeatherWrapperLocal, т.е. главный (@Embedded) объект
    @Query("SELECT * from weatherWrapperLocal")
    fun getWeatherLocalList(): LiveData<List<WeatherEntityResultLocal>>
}