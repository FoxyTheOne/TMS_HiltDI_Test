package com.myproject.hiltdi.di

import android.content.Context
import androidx.room.Room
import com.myproject.hiltdi.data.dataSource.local.ILocalWeatherDataSource
import com.myproject.hiltdi.data.dataSource.network.IRemoteWeatherDataSource
import com.myproject.hiltdi.data.dataSource.local.LocalWeatherDataSource
import com.myproject.hiltdi.data.dataSource.network.RemoteWeatherDataSource
import com.myproject.hiltdi.data.localDatabaseRoom.AppRoomDatabaseAbstract
import com.myproject.hiltdi.data.localDatabaseRoom.IWeatherDAO
import com.myproject.hiltdi.data.localStorage.sharedPreference.AppSharedPreference
import com.myproject.hiltdi.data.sharedPreference.IAppSharedPreference
import com.myproject.hiltdi.data.networkService.WeatherService
import com.myproject.hiltdi.data.repository.WeatherRepository
import com.myproject.hiltdi.domain.iWeatherRepository.IWeatherRepository
import com.myproject.hiltdi.domain.weatherInteractor.IWeatherInteractor
import com.myproject.hiltdi.domain.weatherInteractor.WeatherInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    companion object {
        // ROOM -> 1. База данных Room
        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext appContext: Context): AppRoomDatabaseAbstract {
            val roomDatabase = Room.databaseBuilder(
                appContext,
                AppRoomDatabaseAbstract::class.java,
                "AppRoomDatabase"
            ).build()

            return roomDatabase
        }
    }

//    @Binds
//    @Singleton
//    abstract fun bindSharedPreference(
//        appSharedPreference: AppSharedPreference
//    ) : IAppSharedPreference

}

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindsViewModelModules {

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/"

        @Provides
        fun bindWeatherService(): WeatherService {
            // RETROFIT -> 2. Создаём ретрофит сервис на основе ранее созданного сервиса WeatherService (билдер)
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // RETROFIT -> 3. Чтобы инициализировать интерфейс, обращаться к нему и вызывать определенные методы, мы должны обратиться к созданному ретрофиту,
            // вызвать метод create и передать туда класс, на основе которого мы должны построить сервис
            return retrofit.create(WeatherService::class.java)
        }

        // ROOM -> 2. Объекты для обращения к Dao
        @Provides
        fun provideWeatherDao(appDatabase: AppRoomDatabaseAbstract): IWeatherDAO {
            return appDatabase.getWeatherDAO()
        }

    }

    @Binds
//    @Singleton
    abstract fun bindSharedPreference(
        appSharedPreference: AppSharedPreference
    ) : IAppSharedPreference

    @Binds
    abstract fun bindRemoteWeatherDataSource(
        remoteWeatherDataSource: RemoteWeatherDataSource
    ) : IRemoteWeatherDataSource

    @Binds
    abstract fun bindLocalWeatherDataSource(
        localWeatherDataSource: LocalWeatherDataSource
    ) : ILocalWeatherDataSource

    @Binds
    abstract fun bindWeatherInteractor(
        weatherInteractor: WeatherInteractor
    ): IWeatherInteractor

    @Binds
    abstract fun bindWeatherRepository(
        weatherRepository: WeatherRepository
    ): IWeatherRepository

}