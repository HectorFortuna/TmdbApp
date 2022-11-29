package com.hectorfortuna.tmdbapp.data.network

import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")apikey:String,
        @Query("page")page: Int
    ): PopularResponse
}