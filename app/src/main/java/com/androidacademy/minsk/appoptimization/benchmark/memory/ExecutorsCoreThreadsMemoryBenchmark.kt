package com.androidacademy.minsk.appoptimization.benchmark.memory

import android.content.Context
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ExecutorsCoreThreadsMemoryBenchmark(context: Context, uiScope: CoroutineScope) :
    Benchmark("Memory test 4: executor coreThreads test", context, uiScope) {

    override fun benchmark() {
        for (i in 1..100) {
            ThreadPoolExecutor(10, 10, 30, TimeUnit.SECONDS, ArrayBlockingQueue(50))
                .prestartAllCoreThreads()
        }
    }
}