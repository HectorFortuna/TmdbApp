package com.hectorfortuna.tmdbapp.data.network.api

import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apikey: String,
        @Query("page") page: Int
    ): Response<PopularResponse>

    // TODO add adult filter
    @GET("3/search/movie")
    suspend fun searchMovie(
        @Query("api_key") apikey: String,
        @Query("query") query: String
    ): Response<PopularResponse>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")movieId: Int,
        @Query("api_key")apikey: String
    ): Response<MovieDetails>
}