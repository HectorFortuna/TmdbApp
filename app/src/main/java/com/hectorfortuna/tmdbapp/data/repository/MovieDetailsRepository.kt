package com.hectorfortuna.tmdbapp.data.repository

import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import retrofit2.Response

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int,apikey: String ): Response<MovieDetails>
}