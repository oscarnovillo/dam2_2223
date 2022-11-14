package com.example.hiltmenu

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class RoomApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }

}