package com.hectorfortuna.tmdbapp

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import com.hectorfortuna.tmdbapp.cache.hawk.ModuleHawk
import com.hectorfortuna.tmdbapp.core.ViewManager
import com.hectorfortuna.tmdbapp.core.network.ConnectivityWatcher
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