package com.hectorfortuna.tmdbapp.data.network.repository

import com.hectorfortuna.tmdbapp.data.network.model.moviedetails.MovieDetails
import retrofit2.Response

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int,apikey: String ): Response<MovieDetails>
}