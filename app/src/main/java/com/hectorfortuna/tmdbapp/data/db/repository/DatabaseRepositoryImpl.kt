package com.hectorfortuna.tmdbapp.data.db.repository

import androidx.lifecycle.LiveData
import com.hectorfortuna.tmdbapp.data.db.MovieDetailsDAO
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val dao: MovieDetailsDAO): DatabaseRepository{
    override suspend fun insertFavourite(favourites: MovieDetails) =
        dao.insertFavouriteMovies(favourites)


    override suspend fun deleteFavourite(favourites: MovieDetails) =
        dao.deleteCharacters(favourites)


    override suspend fun getFavouritesMovies(favouriteId: Int): MovieDetails? =
        dao.getFavouriteMovies(favouriteId)


    override fun getAllMovies(): LiveData<List<MovieDetails>> =
        dao.getAllMovies()
}