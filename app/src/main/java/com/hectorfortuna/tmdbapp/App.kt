package com.hectorfortuna.tmdbapp

import android.app.Application
import com.hectorfortuna.tmdbapp.cache.hawk.ModuleHawk
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
            Timber.plant(Timber.DebugTree())
            ModuleHawk.init(this)
    }
}