package com.hectorfortuna.tmdbapp.cache.usecase.savecache

import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.data.model.popular.Result

interface SaveCacheUseCase {
    suspend fun saveCache(resultList: List<Result>, key: CacheKeys): Boolean
}