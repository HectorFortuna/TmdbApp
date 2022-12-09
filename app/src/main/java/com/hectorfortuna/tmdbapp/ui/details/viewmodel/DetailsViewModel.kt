package com.hectorfortuna.tmdbapp.ui.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorfortuna.tmdbapp.data.core.State
import com.hectorfortuna.tmdbapp.data.di.qualifiers.Io
import com.hectorfortuna.tmdbapp.data.network.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.network.usecase.details.MovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: MovieDetailsUseCase,
    @Io val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _response = MutableLiveData<State<MovieDetails>>()
    val response: LiveData<State<MovieDetails>> get() = _response

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
}