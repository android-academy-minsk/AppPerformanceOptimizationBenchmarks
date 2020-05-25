package com.androidacademy.minsk.appoptimization.memoryleak

object SomeDataProvider {
    private val listeners: MutableList<SomeListener> = mutableListOf()

    fun registerListener(listener: SomeListener) {
        listeners.add(listener)
    }
}