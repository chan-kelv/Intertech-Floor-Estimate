package com.intertech.floorestimator

import android.app.Application
import timber.log.Timber

class FloorEstimatorApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}