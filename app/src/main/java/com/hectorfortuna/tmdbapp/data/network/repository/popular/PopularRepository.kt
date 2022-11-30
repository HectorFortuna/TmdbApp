package com.hectorfortuna.tmdbapp.data.network.repository.popular

import com.hectorfortuna.tmdbapp.data.network.model.popular.PopularResponse
import retrofit2.Response

interface PopularRepository {
    suspend fun getPopularMovies(apikey: String, page: Int): Response<PopularResponse>
    suspend fun searchMovie(apikey: String, query: String): Response<PopularResponse>
}