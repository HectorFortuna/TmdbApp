package com.hectorfortuna.tmdbapp.cache.usecase.containscache

import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys

interface ContainsCacheUseCase {
    fun <T> cacheExistAndIsNotNull(key: CacheKeys): Boolean
}