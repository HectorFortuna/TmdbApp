package com.hectorfortuna.tmdbapp.data.di.module

import com.hectorfortuna.tmdbapp.data.network.repository.MovieDetailsRepository
import com.hectorfortuna.tmdbapp.data.network.repository.MovieDetailsRepositoryImpl
import com.hectorfortuna.tmdbapp.data.network.repository.popular.PopularRepository
import com.hectorfortuna.tmdbapp.data.network.repository.popular.PopularRepositoryImpl
import com.hectorfortuna.tmdbapp.data.network.usecase.details.MovieDetailsUseCase
import com.hectorfortuna.tmdbapp.data.network.usecase.details.MovieDetailsUseCaseImpl
import com.hectorfortuna.tmdbapp.data.network.usecase.popular.PopularUseCase
import com.hectorfortuna.tmdbapp.data.network.usecase.popular.PopularUseCaseImpl
import com.hectorfortuna.tmdbapp.data.network.usecase.search.SearchUseCase
import com.hectorfortuna.tmdbapp.data.network.usecase.search.SearchUseCaseImpl
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
}