package com.hectorfortuna.tmdbapp.cache.usecase.getcache

import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.data.model.popular.Result

interface GetCacheUseCase {
    suspend fun getPopular(keys: CacheKeys): List<Result>
}