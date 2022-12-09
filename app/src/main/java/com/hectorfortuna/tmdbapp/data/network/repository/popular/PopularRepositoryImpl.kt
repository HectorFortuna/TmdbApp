package com.hectorfortuna.tmdbapp.data.network.repository.popular

import com.hectorfortuna.tmdbapp.data.network.api.Service
import com.hectorfortuna.tmdbapp.data.network.model.popular.PopularResponse
import retrofit2.Response
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(private val api: Service) : PopularRepository {

    override suspend fun getPopularMovies(apikey: String, page: Int): Response<PopularResponse> =
        api.getPopularMovies(apikey, page)

    override suspend fun searchMovie(apikey: String, query: String): Response<PopularResponse> =
        api.searchMovie(apikey, query)
}