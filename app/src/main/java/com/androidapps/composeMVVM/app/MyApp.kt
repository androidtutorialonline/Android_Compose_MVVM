package com.androidapps.composeMVVM.app

import android.app.Application
import com.androidapps.composeMVVM.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Custom Application class for initializing application-wide resources.
 * The `@HiltAndroidApp` annotation triggers Hilt's code generation and components.
 */
@HiltAndroidApp
class MyApp : Application()  {
    /**
     * Initializes application-level resources when the application is created.
     * In debug builds, this method sets up Timber for logging.
     */

    override fun onCreate() {
        super.onCreate()
        // Initialize Timber logging only in debug builds
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
