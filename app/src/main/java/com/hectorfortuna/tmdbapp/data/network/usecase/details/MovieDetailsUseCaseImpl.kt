package com.hectorfortuna.tmdbapp.data.network.usecase.details

import com.hectorfortuna.tmdbapp.data.network.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.network.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsUseCaseImpl @Inject constructor(private val repository: MovieDetailsRepository) :
    MovieDetailsUseCase {
    override suspend fun getMovieDetails(apiKey: String, movieId: Int): MovieDetails? {
        val response = repository.getMovieDetails(apiKey, movieId)

        return if (response.isSuccessful && response.code() == 200) {
            response.body()
        } else {
            null
        }
    }
}