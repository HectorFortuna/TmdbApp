package com.hectorfortuna.tmdbapp.cache.repository

import com.hectorfortuna.tmdbapp.cache.hawk.CacheKeys

interface CacheRepository {
    fun <T> existAndIsNotNull(key: CacheKeys): Boolean
    fun <T> add(key: CacheKeys, obj: T): Boolean
    fun <T> delete(key: CacheKeys): Boolean
    fun <T> get(key: CacheKeys): T
    fun clearRepository():Boolean
}