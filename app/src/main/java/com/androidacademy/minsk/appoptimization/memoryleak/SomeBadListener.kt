package com.androidacademy.minsk.appoptimization.memoryleak

import android.app.Activity

class SomeBadListener(val myActivityRef: Activity) : SomeListener {
    override fun onEvent(event: Any) { /* do something */
    }
}