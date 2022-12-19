package com.hectorfortuna.tmdbapp.di.module

import com.hectorfortuna.tmdbapp.cache.hawk.ModuleHawk
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object HawkModule {

    @Singleton
    @Provides
    fun init(): ModuleHawk = ModuleHawk
}