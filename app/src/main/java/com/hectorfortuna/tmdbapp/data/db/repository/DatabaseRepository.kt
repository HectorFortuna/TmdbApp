package com.hectorfortuna.tmdbapp.data.db.repository

import androidx.lifecycle.LiveData
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails

interface DatabaseRepository {
    suspend fun insertFavourite(favourites: MovieDetails)
    suspend fun deleteFavourite(favourites: MovieDetails)
    suspend fun getFavouritesMovies(favouriteId: Int): MovieDetails?
    fun getAllMovies(): LiveData<List<MovieDetails>>
}