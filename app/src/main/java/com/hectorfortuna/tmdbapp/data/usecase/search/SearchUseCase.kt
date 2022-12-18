package com.hectorfortuna.tmdbapp.data.usecase.search

import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse

interface SearchUseCase {
    suspend fun searchMovie(apikey:String, query: String): PopularResponse?
}