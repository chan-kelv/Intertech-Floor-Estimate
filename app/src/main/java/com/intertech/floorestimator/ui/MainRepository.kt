package com.intertech.floorestimator.ui

class MainRepository private constructor() {
    companion object {
        @Volatile private var instance: MainRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MainRepository().also { instance = it }
        }
    }
}