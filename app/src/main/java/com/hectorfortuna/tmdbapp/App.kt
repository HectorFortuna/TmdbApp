package com.hectorfortuna.tmdbapp

import android.app.Application
import com.hectorfortuna.tmdbapp.cache.hawk.ModuleHawk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            ModuleHawk.init(this)
        }
    }
}