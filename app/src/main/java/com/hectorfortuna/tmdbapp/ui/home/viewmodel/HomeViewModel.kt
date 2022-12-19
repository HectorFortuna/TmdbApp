package com.hectorfortuna.tmdbapp.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorfortuna.tmdbapp.cache.usecase.savecache.SaveCacheUseCase
import com.hectorfortuna.tmdbapp.core.State
import com.hectorfortuna.tmdbapp.di.qualifiers.Io
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import com.hectorfortuna.tmdbapp.data.usecase.popular.PopularUseCase
import com.hectorfortuna.tmdbapp.data.usecase.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: PopularUseCase,
    private val searchUseCase: SearchUseCase,
    private val saveUseCase:SaveCacheUseCase,
    @Io val ioDispatcher: CoroutineDispatcher
): ViewModel(){

    private val _response = MutableLiveData<State<PopularResponse>>()
    val response: LiveData<State<PopularResponse>>
        get() = _response


    private val _search = MutableLiveData<State<PopularResponse>>()
    val search: LiveData<State<PopularResponse>>
        get() = _search

    fun getPopularMovies(apikey: String , page: Int){
        viewModelScope.launch {
            try {
                _response.value = State.loading(true)
                val response = withContext(ioDispatcher){
                    useCase.getPopularMovies(apikey, page)
                }
                _response.value = State.success(response)
            } catch (throwable: Throwable){
                _response.value = State.error(throwable)
            }
        }
    }

    fun searchMovie(apikey: String, query: String){
        viewModelScope.launch {
            try {
                _search.value = State.loading(true)
                val searchResponse = withContext(ioDispatcher){
                    searchUseCase.searchMovie(apikey, query)
                }
                _search.value = State.success(searchResponse)
            } catch (throwable: Throwable){
                _search.value = State.error(throwable)
            }
        }
    }

    private suspend fun saveInCache(data: com.hectorfortuna.tmdbapp.data.model.popular.Result){
        data.let {
            saveUseCase.saveCache(it,"movies")
        }
    }

}