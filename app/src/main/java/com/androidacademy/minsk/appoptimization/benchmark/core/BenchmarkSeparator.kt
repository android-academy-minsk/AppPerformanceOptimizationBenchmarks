package com.androidacademy.minsk.appoptimization.benchmark.core

import android.content.Context
import kotlinx.coroutines.CoroutineScope

//One more "awesome" kludge. Needs only for display benchmarks separator. Too lazy to make a more elegant decision
class BenchmarkSeparator(name: String, context: Context, uiScope: CoroutineScope) :
    Benchmark(name, context, uiScope) {
    override fun benchmark() {
        //no-op
    }
}