package com.hectorfortuna.tmdbapp.data.di.module

import com.hectorfortuna.tmdbapp.data.model.repository.popular.PopularRepository
import com.hectorfortuna.tmdbapp.data.model.repository.popular.PopularRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun bindPopularRepository(
        popularRepository: PopularRepositoryImpl
    ):PopularRepository
}