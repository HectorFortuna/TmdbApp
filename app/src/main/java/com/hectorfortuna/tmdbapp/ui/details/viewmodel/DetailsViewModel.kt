package com.hectorfortuna.tmdbapp.ui.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorfortuna.tmdbapp.data.core.State
import com.hectorfortuna.tmdbapp.data.db.usecase.DatabaseUseCase
import com.hectorfortuna.tmdbapp.data.di.qualifiers.Io
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.usecase.details.MovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: MovieDetailsUseCase,
    private val dbUseCase:DatabaseUseCase,
    @Io val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _response = MutableLiveData<State<MovieDetails>>()
    val response: LiveData<State<MovieDetails>> get() = _response

    private val _verifySavedMovie = MutableLiveData<State<Boolean>>()
    val verifySavedMovie: LiveData<State<Boolean>> get() = _verifySavedMovie

    private val _delete = MutableLiveData<State<Boolean>>()
    val delete: LiveData<State<Boolean>> get() = _delete

    fun getMovieDetails(apikey:String, movieId: Int){
        viewModelScope.launch {
            try {
                _response.value = State.loading(true)
                val response = withContext(ioDispatcher){
                    useCase.getMovieDetails( movieId, apikey)
                }
                _response.value = State.success(response)
            } catch (throwable: Throwable){
                _response.value = State.error(throwable)
            }
        }
    }

    fun insertFavourite(favourites: MovieDetails){
        viewModelScope.launch {
            dbUseCase.insertFavourite(favourites)
        }
    }

    fun verifySavedMovie(favouriteId: Int){
        viewModelScope.launch {
            try {
                val result = withContext(ioDispatcher){
                    dbUseCase.getFavouritesMovies(favouriteId)
                }
                _verifySavedMovie.value = State.success(result != null)
            } catch (throwable: Throwable){
                _verifySavedMovie.value = State.error(throwable)
            }
        }
    }

    fun deleteFavourite(favourites: MovieDetails) =
        viewModelScope.launch{
        try {
            withContext(ioDispatcher){
                dbUseCase.deleteFavourite(favourites)
            }
            _delete.value = State.success(true)
        } catch (throwable: Throwable){
            _delete.value = State.error(throwable)
        }
    }
}