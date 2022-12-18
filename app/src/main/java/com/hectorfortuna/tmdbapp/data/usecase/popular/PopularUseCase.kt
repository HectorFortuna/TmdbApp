package com.hectorfortuna.tmdbapp.data.usecase.popular

import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse

interface PopularUseCase{

    suspend fun getPopularMovies(apikey: String, page: Int): PopularResponse?
}