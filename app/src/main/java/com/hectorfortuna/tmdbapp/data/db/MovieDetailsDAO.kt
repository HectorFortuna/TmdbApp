package com.hectorfortuna.tmdbapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails

@Dao
interface MovieDetailsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavouriteMovies(favourites: MovieDetails)

    @Query("SELECT * FROM movie_details_table")
    fun getAllMovies(): LiveData<List<MovieDetails>>

    @Delete
    suspend fun deleteCharacters(favourites: MovieDetails)

    @Query("SELECT * FROM movie_details_table WHERE id = :favouriteId ")
    suspend fun getFavouriteMovies(favouriteId: Int): MovieDetails?

    @Query("SELECT * FROM movie_details_table WHERE id = :id ")
    fun getFavouriteMovieById(id: Int): MovieDetails?
}