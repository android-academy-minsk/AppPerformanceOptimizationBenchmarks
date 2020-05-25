package com.androidacademy.minsk.appoptimization.benchmark.core

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BenchmarkGroup(name: String, context: Context, uiScope: CoroutineScope) :
    Benchmark(name, context, uiScope) {

    val benchmarks: MutableList<Benchmark> = mutableListOf()

    override fun benchmark() {
        Log.i("BenchmarkGroup", "start execute group $name")
        val timeStart = System.currentTimeMillis()

        benchmarks.forEach { it.execute() }

        val timeTotal = System.currentTimeMillis() - timeStart
        Log.i(
            "Benchmark",
            "$name - total group time = $timeTotal  \n=====================\n"
        )
        uiScope.launch {
            Toast.makeText(context, "BenchmarkGroup $name - time = $timeTotal", Toast.LENGTH_LONG)
                .show()
        }
    }
}