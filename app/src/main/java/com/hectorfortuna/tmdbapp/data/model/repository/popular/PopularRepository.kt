package com.hectorfortuna.tmdbapp.data.model.repository.popular

import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse

interface PopularRepository {
    suspend fun getPopularMovies(apikey: String, page: Int): PopularResponse
}