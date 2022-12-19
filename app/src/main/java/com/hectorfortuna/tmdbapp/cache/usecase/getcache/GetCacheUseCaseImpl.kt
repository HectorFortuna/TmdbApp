package com.hectorfortuna.tmdbapp.cache.usecase.getcache

import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.cache.repository.CacheRepository
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import javax.inject.Inject

class GetCacheUseCaseImpl @Inject constructor(
    private val repository: CacheRepository
) : GetCacheUseCase {
    override suspend fun getPopular(keys: CacheKeys): PopularResponse =
        repository.get(keys)

}