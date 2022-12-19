package com.hectorfortuna.tmdbapp.di.module

import com.hectorfortuna.tmdbapp.cache.repository.CacheRepository
import com.hectorfortuna.tmdbapp.cache.repository.CacheRepositoryImpl
import com.hectorfortuna.tmdbapp.cache.usecase.savecache.SaveCacheUseCase
import com.hectorfortuna.tmdbapp.cache.usecase.savecache.SaveCacheUseCaseImpl
import com.hectorfortuna.tmdbapp.data.db.repository.DatabaseRepository
import com.hectorfortuna.tmdbapp.data.db.repository.DatabaseRepositoryImpl
import com.hectorfortuna.tmdbapp.data.db.usecase.DatabaseUseCase
import com.hectorfortuna.tmdbapp.data.db.usecase.DatabaseUseCaseImpl
import com.hectorfortuna.tmdbapp.data.repository.MovieDetailsRepository
import com.hectorfortuna.tmdbapp.data.repository.MovieDetailsRepositoryImpl
import com.hectorfortuna.tmdbapp.data.repository.popular.PopularRepository
import com.hectorfortuna.tmdbapp.data.repository.popular.PopularRepositoryImpl
import com.hectorfortuna.tmdbapp.data.usecase.details.MovieDetailsUseCase
import com.hectorfortuna.tmdbapp.data.usecase.details.MovieDetailsUseCaseImpl
import com.hectorfortuna.tmdbapp.data.usecase.popular.PopularUseCase
import com.hectorfortuna.tmdbapp.data.usecase.popular.PopularUseCaseImpl
import com.hectorfortuna.tmdbapp.data.usecase.search.SearchUseCase
import com.hectorfortuna.tmdbapp.data.usecase.search.SearchUseCaseImpl
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
    ): PopularRepository

    @Singleton
    @Binds
    abstract fun bindDetailsRepository(
        movieDetailsRepository: MovieDetailsRepositoryImpl
    ): MovieDetailsRepository

    @Singleton
    @Binds
    abstract fun bindPopularUseCase(
        popularUseCase: PopularUseCaseImpl
    ): PopularUseCase

    @Singleton
    @Binds
    abstract fun bindMovieDetailsUseCase(
        movieDetailsUseCase: MovieDetailsUseCaseImpl
    ): MovieDetailsUseCase

    @Singleton
    @Binds
    abstract fun bindSearchUseCase(
        searchUseCase: SearchUseCaseImpl
    ): SearchUseCase

    @Singleton
    @Binds
    abstract fun bindDatabaseRepository(
        databaseRepository: DatabaseRepositoryImpl
    ): DatabaseRepository

    @Singleton
    @Binds
    abstract fun bindDatabaseUseCase(
        databaseUseCase: DatabaseUseCaseImpl
    ): DatabaseUseCase

    @Singleton
    @Binds
    abstract fun bindCacheRepository(
        cacheRepository: CacheRepositoryImpl
    ): CacheRepository

    @Singleton
    @Binds
    abstract fun bindSaveCacheUseCase(
        saveCache: SaveCacheUseCaseImpl
    ): SaveCacheUseCase
}