package com.hectorfortuna.tmdbapp.cache.repository

import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys
import com.hectorfortuna.tmdbapp.cache.hawk.ModuleHawk
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(
    private val hawk: ModuleHawk
) : CacheRepository {
    override fun <T> existAndIsNotNull(key: CacheKeys): Boolean {
        return if (hawk.contains(key.key)) {
            val cacheObj = hawk.get<T>(key.key)
            cacheObj != null
        } else false
    }

    override fun <T> add(key: CacheKeys, obj: T): Boolean = try {
        if (hawk.contains(key.key))
            hawk.delete(key.key)

        hawk.put(key.key, obj)
        true
    } catch (t: Throwable) {
        false
    }

    override fun <T> delete(key: CacheKeys): Boolean = try {
        if (hawk.contains(key.key))
            hawk.delete(key.key)
        true
    } catch (t: Throwable) {
        false
    }

    override fun <T> get(key: CacheKeys): T = hawk.get(key.key)

    override fun clearRepository(): Boolean = try {
        CacheKeys.values().forEach {
            if (hawk.contains(it.key))
                hawk.delete(it.key)
        }
        true
    } catch (t: Throwable) {
        false
    }
}