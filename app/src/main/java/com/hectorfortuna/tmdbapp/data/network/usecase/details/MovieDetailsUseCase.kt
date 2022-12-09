package com.hectorfortuna.tmdbapp.data.network.usecase.details

import com.hectorfortuna.tmdbapp.data.network.model.moviedetails.MovieDetails

interface MovieDetailsUseCase {
    suspend fun getMovieDetails( movieId: Int, apiKey: String): MovieDetails?
}