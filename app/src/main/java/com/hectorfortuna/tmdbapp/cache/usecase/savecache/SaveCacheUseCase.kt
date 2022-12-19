package com.hectorfortuna.tmdbapp.cache.usecase.savecache

import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse

interface SaveCacheUseCase {
    suspend fun saveCache(resultList: PopularResponse?, key: CacheKeys): Boolean
}