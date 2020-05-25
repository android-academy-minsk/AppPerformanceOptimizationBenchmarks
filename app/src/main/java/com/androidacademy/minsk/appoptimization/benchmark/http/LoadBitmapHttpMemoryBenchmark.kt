package com.androidacademy.minsk.appoptimization.benchmark.http

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadBitmapHttpMemoryBenchmark(private val imageView: ImageView, uiScope: CoroutineScope) :
    Benchmark("HTTP test 1: load bitmap", imageView.context, uiScope) {

    override fun benchmark() {
        uiScope.launch {
            imageView.visibility = View.VISIBLE
            //disable cache and load bitmap
            Glide.with(context)
                .setDefaultRequestOptions(RequestOptions.skipMemoryCacheOf(true))
                .load("https://wallpaperaccess.com/full/24905.jpg")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView)
        }
    }
}