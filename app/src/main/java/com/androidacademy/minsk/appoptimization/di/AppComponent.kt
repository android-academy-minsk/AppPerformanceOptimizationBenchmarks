package com.androidacademy.minsk.appoptimization.di


import com.androidacademy.minsk.appoptimization.MainActivity
import com.androidacademy.minsk.appoptimization.MyApplication
import com.androidacademy.minsk.appoptimization.memoryleak.LeakActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RoomModule::class))
interface AppComponent {

    fun inject(application: MyApplication)

    fun inject(activity: MainActivity)
    fun inject(activity: LeakActivity)

}