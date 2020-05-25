package com.androidacademy.minsk.appoptimization.benchmark.memory

import android.content.Context
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import kotlinx.coroutines.CoroutineScope

class AllocateSmallObjectsMemoryBenchmark(context: Context, uiScope: CoroutineScope) :
    Benchmark("Memory test 2: allocate small objects test", context, uiScope) {

    override fun benchmark() {
        for (i in 1..5000000) {
            var o1 = Any()
        }
    }
}