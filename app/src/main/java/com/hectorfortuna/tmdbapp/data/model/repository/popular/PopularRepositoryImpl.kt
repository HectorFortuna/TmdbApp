package com.hectorfortuna.tmdbapp.data.model.repository.popular

import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import com.hectorfortuna.tmdbapp.data.network.Service
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(private val api: Service) : PopularRepository {
    override suspend fun getPopularMovies(
        apikey: String,
        page: Int
    ): PopularResponse =
        api.getPopularMovies(apikey, page)

}