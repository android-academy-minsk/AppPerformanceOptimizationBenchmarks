package com.androidacademy.minsk.appoptimization.benchmark.cpu

import android.view.View
import android.view.animation.AlphaAnimation
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AnimateHiddenViewCpuBenchmarkTest(private val view: View, uiScope: CoroutineScope) :
    Benchmark("Run animate view test", view.context, uiScope) {

    override fun benchmark() {
        uiScope.launch {
            val animation = AlphaAnimation(0.1f, 1.0f)
            animation.duration = 20000
            view.startAnimation(animation) //start animation
            view.visibility = View.GONE  //hide view. animation still running
        }
    }
}