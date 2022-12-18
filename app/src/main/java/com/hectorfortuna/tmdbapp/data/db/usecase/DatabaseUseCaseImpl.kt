package com.hectorfortuna.tmdbapp.data.db.usecase

import androidx.lifecycle.LiveData
import com.hectorfortuna.tmdbapp.data.db.repository.DatabaseRepository
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import javax.inject.Inject

class DatabaseUseCaseImpl @Inject constructor(private val repository: DatabaseRepository) :
    DatabaseUseCase {
    override suspend fun insertFavourite(favourites: MovieDetails) =
        try {
            repository.insertFavourite(favourites)
        } catch (throwable: Throwable){
            throw throwable
        }

    override suspend fun deleteFavourite(favourites: MovieDetails) =
        repository.deleteFavourite(favourites)

    override suspend fun getFavouritesMovies(favouriteId: Int): MovieDetails? =
        repository.getFavouritesMovies(favouriteId)

    override fun getAllMovies(): LiveData<MovieDetails> =
        repository.getAllMovies()

}