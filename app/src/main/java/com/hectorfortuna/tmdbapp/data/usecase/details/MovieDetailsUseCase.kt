package com.hectorfortuna.tmdbapp.data.usecase.details

import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails

interface MovieDetailsUseCase {
    suspend fun getMovieDetails( movieId: Int, apiKey: String): MovieDetails?
}