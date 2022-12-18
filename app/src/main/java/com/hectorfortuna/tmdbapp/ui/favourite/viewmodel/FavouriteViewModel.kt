package com.hectorfortuna.tmdbapp.ui.favourite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorfortuna.tmdbapp.data.core.State
import com.hectorfortuna.tmdbapp.data.db.repository.DatabaseRepository
import com.hectorfortuna.tmdbapp.data.db.usecase.DatabaseUseCase
import com.hectorfortuna.tmdbapp.data.di.qualifiers.Io
import com.hectorfortuna.tmdbapp.data.model.moviedetails.MovieDetails
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import com.hectorfortuna.tmdbapp.data.usecase.popular.PopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val useCase: DatabaseUseCase,
    private val popularUseCase: PopularUseCase,
    @Io val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _delete = MutableLiveData<State<Boolean>>()
    val delete: LiveData<State<Boolean>>
        get() = _delete

    private val _response = MutableLiveData<State<PopularResponse>>()
    val response: LiveData<State<PopularResponse>>
        get() = _response

    fun getPopularMovies(apikey: String , page: Int){
        viewModelScope.launch {
            try {
                _response.value = State.loading(true)
                val response = withContext(ioDispatcher){
                    popularUseCase.getPopularMovies(apikey, page)
                }
                _response.value = State.success(response)
            } catch (throwable: Throwable){
                _response.value = State.error(throwable)
            }
        }
    }

    fun getMovies() = useCase.getAllMovies()

    fun deleteFavourite(favourites: MovieDetails) =
        viewModelScope.launch {
            try {
                withContext(ioDispatcher) {
                    useCase.deleteFavourite(favourites)
                }
                _delete.value = State.success(true)
            } catch (throwable: Throwable) {
                _delete.value = State.error(throwable)
            }
        }
}