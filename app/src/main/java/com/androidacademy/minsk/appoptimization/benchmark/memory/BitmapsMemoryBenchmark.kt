package com.androidacademy.minsk.appoptimization.benchmark.memory

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import com.example.profiling.R
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import kotlinx.coroutines.CoroutineScope

class BitmapsMemoryBenchmark(
    context: Context,
    private val resources: Resources,
    uiScope: CoroutineScope
) : Benchmark("Memory test 5: bitmaps test", context, uiScope) {
    private val bitmapsXxxhdpi = mutableListOf<Bitmap>()
    private val bitmapsHdpi = mutableListOf<Bitmap>()
    private val bitmapsNoDpi = mutableListOf<Bitmap>()
    private val drawablesList = mutableListOf<Drawable>()

    override fun benchmark() {
        bitmapsXxxhdpi.add(BitmapFactory.decodeResource(resources, R.drawable.image_xxxhdpi))
        bitmapsHdpi.add(BitmapFactory.decodeResource(resources, R.drawable.image_hdpi))
        bitmapsNoDpi.add(BitmapFactory.decodeResource(resources, R.drawable.image_no_dpi))

        drawablesList.add(resources.getDrawable(R.drawable.image_xxxhdpi))
    }
}