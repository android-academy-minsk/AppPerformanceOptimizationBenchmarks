package com.androidacademy.minsk.appoptimization.benchmark.memory

import android.content.Context
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import kotlinx.coroutines.CoroutineScope

class RunThreadsMemoryBenchmark(context: Context, uiScope: CoroutineScope) :
    Benchmark("Memory test 3: run threads", context, uiScope) {

    override fun benchmark() {
        for (i in 1..5000000) {
            Thread { Thread.sleep(30000) }.start()
        }
    }
}