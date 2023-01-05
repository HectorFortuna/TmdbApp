package com.hectorfortuna.tmdbapp.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.cache.usecase.containscache.ContainsCacheUseCase
import com.hectorfortuna.tmdbapp.cache.usecase.getcache.GetCacheUseCase
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
    private val saveUseCase: SaveCacheUseCase,
    private val containsUseCase: ContainsCacheUseCase,
    private val getCacheUseCase: GetCacheUseCase,
    @Io val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _response = MutableLiveData<State<PopularResponse>>()
    val response: LiveData<State<PopularResponse>>
        get() = _response

    private val _search = MutableLiveData<State<PopularResponse>>()
    val search: LiveData<State<PopularResponse>>
        get() = _search

    fun getPopularMovies(apikey: String, page: Int) {
        viewModelScope.launch {
            try {
                _response.value = State.loading(true)
                if (shouldGetFromCache(page)) {
                    val apiResponse = useCase.getPopularMovies(apikey, page)
                    shouldSaveInCache(page, apiResponse)
                    _response.value = State.success(apiResponse)
                } else {
                    getFromCache().let {
                        _response.value = State.success(it)
                    }
                }
            } catch (throwable: Throwable) {
                _response.value = State.error(throwable)
            }
        }
    }

    fun searchMovie(apikey: String, query: String) {
        viewModelScope.launch {
            try {
                _search.value = State.loading(true)
                val searchResponse = withContext(ioDispatcher) {
                    searchUseCase.searchMovie(apikey, query)
                }
                _search.value = State.success(searchResponse)
            } catch (throwable: Throwable) {
                _search.value = State.error(throwable)
            }
        }
    }

    private fun shouldGetFromCache(page: Int): Boolean{
        val isInCache = containsUseCase.cacheExistAndIsNotNull<PopularResponse>(CacheKeys.POPULAR_MOVIES)
        if(page == 1 && !isInCache){
            return true
        } else if(page == 1 && isInCache){
            return false
        } else{
            return true
        }
    }

    private suspend fun shouldSaveInCache(page: Int, data: PopularResponse?) {
        if (page == 1) {
            saveInCache(data)
        }
    }

    private suspend fun saveInCache(data: PopularResponse?) {
        data.let {
            saveUseCase.saveCache(it, CacheKeys.POPULAR_MOVIES)
        }
    }

    private suspend fun getFromCache() =
        getCacheUseCase.getPopular(CacheKeys.POPULAR_MOVIES)

}