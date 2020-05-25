package com.androidacademy.minsk.appoptimization

class KotlinStreamTest {

    private fun kotlinStreamTest1() {
        val result = listOf(1, 2, 3, 4, 5)
            .map { n -> n * 2 }
            .first()
    }

    private fun kotlinStreamTest2() {
        val result = listOf(1, 2, 3, 4, 5)
            .asSequence() //as java stream behavior
            .map { n -> n * 2 }
            .first()
    }

}