package com.androidacademy.minsk.appoptimization.benchmark.cpu

import android.content.Context
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import com.androidacademy.minsk.appoptimization.benchmark.core.BenchmarkGroup
import kotlinx.coroutines.CoroutineScope

class CpuBenchmarkTest(context: Context, uiScope: CoroutineScope) :
    BenchmarkGroup("CPU tests", context, uiScope) {

    init {
        benchmarks.add(object : Benchmark("cpu test 1: for loop", context, uiScope) {
            override fun benchmark() {
                testCpu1()
            }
        })

        benchmarks.add(object : Benchmark("cpu test 2: strings equal", context, uiScope) {
            override fun benchmark() {
                testCpu2()
            }
        })

        benchmarks.add(object : Benchmark("cpu test 3: do nothing method", context, uiScope) {
            override fun benchmark() {
                testCpu3()
            }
        })

        benchmarks.add(object : Benchmark("cpu test 4: thread sleep", context, uiScope) {
            override fun benchmark() {
                testCpu4()
            }
        })
    }

    // moved tests code so separate methods to simplify profiling: ne need to know hwo is who in cpy profiler
    private fun testCpu1() {
        var a = 0L
        for (i in 1..4700000) a++
    }

    private fun testCpu2() {
        val str1 =
            "some long stringggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg"
        val str2 = String(str1.toByteArray())
        var a = 0L
        for (i in 1..10000) {
            if (str2 == str1) a++
        }
    }

    private fun testCpu3() = 100

    private fun testCpu4() {
        Thread.sleep(500)
    }
}