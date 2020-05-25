package com.androidacademy.minsk.appoptimization.benchmark.core

import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class Benchmark(
    val name: String,
    val context: Context,
    protected val uiScope: CoroutineScope
) {

    fun execute() {
        val timeStart = System.currentTimeMillis()
        benchmark()
        val timeTotal = System.currentTimeMillis() - timeStart
        Log.i("Benchmark", "$name - time = $timeTotal")

        uiScope.launch {
            Toast.makeText(context, "Benchmark $name - time = $timeTotal", LENGTH_LONG).show()
        }

    }

    abstract fun benchmark()
}