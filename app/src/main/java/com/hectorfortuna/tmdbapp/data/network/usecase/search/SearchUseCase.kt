package com.hectorfortuna.tmdbapp.data.network.usecase.search

import com.hectorfortuna.tmdbapp.data.network.model.popular.PopularResponse

interface SearchUseCase {
    suspend fun searchMovie(apikey:String, query: String): PopularResponse?
}