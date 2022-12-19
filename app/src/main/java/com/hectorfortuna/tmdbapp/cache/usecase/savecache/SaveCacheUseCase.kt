package com.hectorfortuna.tmdbapp.cache.usecase.savecache

import com.hectorfortuna.tmdbapp.data.model.popular.Result

interface SaveCacheUseCase {
    suspend fun saveCache(result: Result, key: String): Boolean
}