package com.androidacademy.minsk.appoptimization.benchmark.memory

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AllocateLargeObjectMemoryBenchmark(context: Context, uiScope: CoroutineScope) :
    Benchmark("Memory test 1: allocate large bitmap", context, uiScope) {

    override fun benchmark() {
        uiScope.launch {
            val bitmap = Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val paint = Paint()
            paint.color = Color.BLACK
            paint.style = Paint.Style.FILL
            canvas.drawPaint(paint)
        }
    }
}