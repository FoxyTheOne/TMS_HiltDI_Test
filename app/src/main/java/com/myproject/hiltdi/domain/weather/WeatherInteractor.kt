package com.myproject.hiltdi.domain.weather

import com.myproject.hiltdi.di.LocalWeatherDataSourceQualifier
import com.myproject.hiltdi.di.RemoteWeatherDataSourceQualifier
import com.myproject.hiltdi.domain.iDataSource.IWeatherDataSource
import com.myproject.hiltdi.model.presentation.WeatherPresentation
import com.myproject.hiltdi.model.remote.WeatherRemote
import javax.inject.Inject

/**
 * 3. Interactor делает запрос в локальную базу данных и возвращает в view model либо данные, либо пустой массив.
 * И параллельно - запрос в remote базу данных (снова обрабатывает и возвращает данные в view model).
 * Т.обр., для Interactor нужно две data source - local и remote. Все они будут одного типа (IDataSource), следовательно нам нужны квалификаторы hilt
 *
 * Создаём эти классы, общий интерфейс, общий метод getWeather().
 * Создаём квалификаторы для этих классов.
 * Передаём в конструктор WeatherInteractor эти сущности со всеми необходимыми аннотациями.
 * И заполняем инструкцию по созданию в Module для Hilt
 */
class WeatherInteractor @Inject constructor(
    @RemoteWeatherDataSourceQualifier val remoteWeatherDataSource: IWeatherDataSource,
    @LocalWeatherDataSourceQualifier val localWeatherDataSource: IWeatherDataSource
): IWeatherInteractor {
    // Для реализации простого запроса, составим список городов в companion object
    companion object {
        private const val MINSK = "Minsk"
        private const val LVIV = "Lviv"
        private const val NEW_YORK = "New York"
    }

    override suspend fun getWeather(): List<WeatherPresentation> {
        // Сначала сделаем запросы на сервер и получаем IWeather. Преобразуем в WeatherRemote для доступа к полям
        val weatherMinsk = remoteWeatherDataSource.getWeather(MINSK) as WeatherRemote
        val weatherLviv = remoteWeatherDataSource.getWeather(LVIV) as WeatherRemote
        val weatherNewYork = remoteWeatherDataSource.getWeather(NEW_YORK) as WeatherRemote

        // Преобразуем полученные данные в тип WeatherPresentation
        val weatherMinskPresentation = WeatherPresentation.fromRemote(weatherMinsk)
        val weatherLvivPresentation = WeatherPresentation.fromRemote(weatherLviv)
        val weatherNewYorkPresentation = WeatherPresentation.fromRemote(weatherNewYork)

        // Полученные данные записываем в список для передачи в view model
        return listOf(weatherMinskPresentation, weatherLvivPresentation, weatherNewYorkPresentation)
    }
}