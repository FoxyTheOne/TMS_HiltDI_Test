package com.myproject.hiltdi.di

import javax.inject.Qualifier

/**
 * Создаём необходимые аннотации. Затем Hilt должен знать, как предоставить экземпляр типа, который соответствует каждому квалификатору.
 * Для этого, описываем инструкцию в файле Module
 *
 * Создавали для примера. Больше эти квалификаторы в проекте не нужны
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteWeatherDataSourceQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalWeatherDataSourceQualifier