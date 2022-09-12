package com.mirkamol.flowtodoapp

import android.app.Application
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    companion object {
        lateinit var instance:App
        var localeManager:LocaleManager? = null
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeManager!!.setLocale(this)
    }

    override fun onCreate() {
        super.onCreate()
        LocaleManager(this).setLocale(this)
        instance = this
        localeManager = LocaleManager(this)
    }
}