package com.hectorfortuna.tmdbapp.cache.usecase.savecache

import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.cache.repository.CacheRepository
import com.hectorfortuna.tmdbapp.data.model.popular.Result
import javax.inject.Inject

class SaveCacheUseCaseImpl @Inject constructor(private val repository: CacheRepository) :
    SaveCacheUseCase {
    override suspend fun saveCache(result: Result, key: String): Boolean {
        var verify = true
        if(!addToHawk(result, getKey(key))){
            verify = false
        }
        return verify
    }

    private fun getKey(key: String) = when (key) {
        movies -> CacheKeys.POPULAR_MOVIES
        else -> {
            throw Throwable()
        }
    }

    private fun addToHawk(result: Result, key: CacheKeys): Boolean =
        repository.add(key, result)

    companion object {
        const val movies = "movies"
    }
}