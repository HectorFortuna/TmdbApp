package com.hectorfortuna.tmdbapp.data.network.usecase.popular

import com.hectorfortuna.tmdbapp.data.network.model.popular.PopularResponse

interface PopularUseCase{

    suspend fun getPopularMovies(apikey: String, page: Int): PopularResponse?
}