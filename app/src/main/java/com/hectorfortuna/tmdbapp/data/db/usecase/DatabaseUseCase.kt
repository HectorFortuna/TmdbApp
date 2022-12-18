package com.hectorfortuna.tmdbapp.data.db.usecase

import androidx.lifecycle.LiveData
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails

interface DatabaseUseCase {
    suspend fun insertFavourite(favourites: MovieDetails)
    suspend fun deleteFavourite(favourites: MovieDetails)
    suspend fun getFavouritesMovies(favouriteId: Int): MovieDetails?
    fun getAllMovies(): LiveData<MovieDetails>
}