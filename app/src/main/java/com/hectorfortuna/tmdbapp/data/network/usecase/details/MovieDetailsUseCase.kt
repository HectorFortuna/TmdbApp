package com.hectorfortuna.tmdbapp.data.network.usecase.details

import com.hectorfortuna.tmdbapp.data.network.model.moviedetails.MovieDetails

interface MovieDetailsUseCase {
    suspend fun getMovieDetails(apiKey: String, movieId: Int): MovieDetails?
}