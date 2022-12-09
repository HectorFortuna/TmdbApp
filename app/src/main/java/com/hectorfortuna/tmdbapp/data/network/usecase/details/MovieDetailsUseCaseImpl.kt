package com.hectorfortuna.tmdbapp.data.network.usecase.details

import com.hectorfortuna.tmdbapp.data.network.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.network.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsUseCaseImpl @Inject constructor(private val repository: MovieDetailsRepository) :
    MovieDetailsUseCase {
    override suspend fun getMovieDetails(movieId: Int, apiKey: String): MovieDetails? {

        val response = repository.getMovieDetails( movieId,apiKey)

        return if (response.isSuccessful && response.code() == 200) {
            response.body()
        } else {
            null
        }
    }
}