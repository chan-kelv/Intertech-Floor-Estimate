package com.intertech.floorestimator

import android.app.Application
import android.content.Context
import timber.log.Timber

class FloorEstimatorApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        appContext = this
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var appContext: Context
    }
}