package com.myproject.hiltdi.di

import com.myproject.hiltdi.data.localStorage.sharedPreference.AppSharedPreference
import com.myproject.hiltdi.data.localStorage.sharedPreference.IAppSharedPreference
import com.myproject.hiltdi.data.network.weather.WeatherService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
abstract class BindsModule {

    @Binds
    abstract fun bindSharedPreference(
        appSharedPreference: AppSharedPreference
    ) : IAppSharedPreference

}

@Module
@InstallIn(ActivityComponent::class)
class ProvidesModule {
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/"
    }

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

}