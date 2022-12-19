package com.hectorfortuna.tmdbapp.cache.usecase.containscache

import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.cache.repository.CacheRepository
import javax.inject.Inject

class ContainsCacheUseCaseImpl @Inject constructor(
    private val repository: CacheRepository
) : ContainsCacheUseCase {
    override fun <T> cacheExistAndIsNotNull(key: CacheKeys): Boolean =
        repository.existAndIsNotNull<T>(key)
}