package com.hectorfortuna.tmdbapp.data.network.repository

import com.hectorfortuna.tmdbapp.data.network.api.Service
import com.hectorfortuna.tmdbapp.data.network.model.moviedetails.MovieDetails
import retrofit2.Response
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(private val api: Service) :
    MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int, apikey: String): Response<MovieDetails> =
        api.getMovieDetails(movieId, apikey)
}