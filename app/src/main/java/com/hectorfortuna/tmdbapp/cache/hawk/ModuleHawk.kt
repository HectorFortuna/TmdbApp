package com.hectorfortuna.tmdbapp.cache.hawk

import android.content.Context
import com.orhanobut.hawk.Hawk
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


object ModuleHawk {

    fun contains(key: String): Boolean = Hawk.contains(key)

    fun <T> get(key: String): T = Hawk.get(key)

    fun <T> put(key: String, value: T) = Hawk.put(key, value)

    fun delete(key: String) {
        if (Hawk.contains(key)) {
            Hawk.delete(key)
        }
    }

}