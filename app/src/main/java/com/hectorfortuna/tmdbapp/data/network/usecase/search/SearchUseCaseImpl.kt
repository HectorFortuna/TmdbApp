package com.hectorfortuna.tmdbapp.data.network.usecase.search

import android.util.Log
import com.hectorfortuna.tmdbapp.data.network.model.popular.PopularResponse
import com.hectorfortuna.tmdbapp.data.network.repository.popular.PopularRepository
import timber.log.Timber
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(private val repository: PopularRepository) :
    SearchUseCase {
    override suspend fun searchMovie(apikey: String, query: String): PopularResponse? {

        val response = repository.searchMovie(apikey, query)

        return if (response.isSuccessful && response.code() == 200) {
            response.body()
        } else {
            null
        }
    }

}