package com.androidacademy.minsk.appoptimization.di

import android.content.Context

object Components {

    lateinit var appComponent: AppComponent

    fun init(context: Context) {
        appComponent = DaggerAppComponent.builder()
            .roomModule(RoomModule(context))
            .build()
    }
}