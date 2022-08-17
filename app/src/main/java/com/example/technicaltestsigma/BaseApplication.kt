package com.example.technicaltestsigma

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){
    companion object Constants {
        const val TAG = "This-FSM"
        lateinit var context: Context

        fun getAppContext(): Context {
            return context
        }
    }

    override fun onCreate() {
        // Optional: if you distribute your app as App Bundle, provides detection of incomplete
        // installs due to sideloading and helps users reinstall the app from Google Play.
        // https://docs.objectbox.io/android/app-bundle-and-split-apk
//        if (MissingSplitsManagerFactory.create(this).disableAppIfMissingRequiredSplits()) {
//            return // Skip app initialization.
//        }

        super.onCreate()
        context = applicationContext
    }
}