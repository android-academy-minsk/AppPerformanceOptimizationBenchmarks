package com.androidacademy.minsk.appoptimization

import android.app.Application
import com.androidacademy.minsk.appoptimization.di.Components

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Components.init(this)
        Components.appComponent.inject(this)

    }
}