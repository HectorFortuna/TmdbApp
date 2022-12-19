package com.hectorfortuna.tmdbapp.cache.usecase.savecache

import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.cache.repository.CacheRepository
import com.hectorfortuna.tmdbapp.data.model.popular.PopularResponse
import com.hectorfortuna.tmdbapp.data.model.popular.Result
import javax.inject.Inject

class SaveCacheUseCaseImpl @Inject constructor(private val repository: CacheRepository) :
    SaveCacheUseCase {
    override suspend fun saveCache(resultList: PopularResponse?, key: CacheKeys): Boolean {
        return repository.add(key, resultList)
    }
}