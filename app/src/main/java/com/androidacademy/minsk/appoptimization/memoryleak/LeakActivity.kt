package com.androidacademy.minsk.appoptimization.memoryleak

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.profiling.R
import com.androidacademy.minsk.appoptimization.di.Components


class LeakActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        private val someController = SomeController()

        @JvmStatic
        private val someProvider = SomeDataProvider
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Components.appComponent.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_leak)

        someController.registerActivity(this)

        someController.registerListener(object : SomeListener {
            override fun onEvent(event: Any) {
                /*don't call LeakActivity outer method/field */
            }
        })

    }

    override fun onResume() {
        super.onResume()

        someController.registerListener(object : SomeListener {
            override fun onEvent(event: Any) {
                //call LeakActivity outer method/field
                someLeakActivityMethod()
            }
        })
    }


    fun someLeakActivityMethod() = 1000 //do something
}