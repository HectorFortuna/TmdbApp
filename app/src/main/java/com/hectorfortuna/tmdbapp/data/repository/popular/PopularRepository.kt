package com.hectorfortuna.tmdbapp.data.repository.popular

import android.graphics.Movie
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import retrofit2.Response

interface PopularRepository {
    suspend fun getPopularMovies(apikey: String, page: Int): Response<PopularResponse>
    suspend fun searchMovie(apikey: String, query: String): Response<PopularResponse>

}