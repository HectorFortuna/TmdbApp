package com.hectorfortuna.tmdbapp.cache.usecase.getcache

import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.cache.repository.CacheRepository
import com.hectorfortuna.tmdbapp.data.model.popular.Result
import javax.inject.Inject

class GetCacheUseCaseImpl @Inject constructor(
    private val repository: CacheRepository
) : GetCacheUseCase {
    override suspend fun getPopular(keys: CacheKeys): List<Result> =
        repository.get(keys)

}