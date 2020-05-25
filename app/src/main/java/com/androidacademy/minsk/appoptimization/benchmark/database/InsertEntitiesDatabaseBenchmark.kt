package com.androidacademy.minsk.appoptimization.benchmark.database

import android.content.Context
import android.util.Log
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import com.androidacademy.minsk.appoptimization.database.core.TestDatabase
import com.androidacademy.minsk.appoptimization.database.dao.EmployeeDao
import com.androidacademy.minsk.appoptimization.database.model.Employee
import kotlinx.coroutines.*
import kotlin.random.Random

open class InsertEntitiesDatabaseBenchmark(
        context: Context,
        uiScope: CoroutineScope,
        database: dagger.Lazy<TestDatabase>,
        employeeDao: EmployeeDao)
    : BaseDatabaseBenchmarkGroup("Insert Entities DB test", context, uiScope, database, employeeDao) {

    init {
        benchmarks.add(object : Benchmark("Insert Entities test 1: insert without transaction", context, uiScope) {
            override fun benchmark() {
                clearDatabase()
                fillDatabase()
            }
        })

        benchmarks.add(object : Benchmark("Insert Entities test 2: wrap insert to transaction", context, uiScope) {
            override fun benchmark() {
                clearDatabase()
                database.get().runInTransaction {
                    Log.v(name, "runInTransaction start")
                    fillDatabase()
                }
            }
        })
    }

    private fun fillDatabase() {
        Log.v(name, "fillDatabase start")

        for (i in 0..100_000) {
            val employee: Employee = Employee().apply {
                age = Random.nextInt(18, 101)
                ageNoIndex = age
                sex = Random.nextInt(0, 2)
                sexNoIndex = sex
                firstName = "First name $i"
                lastName = "Last name $i"

                bloodType = generateBloodType()
                bloodTypeNoIndex = bloodType
            }

            if (i % 1000 == 0) {
                Log.v(name, "fillDatabase i = $i")
            }
            employeeDao.insert(employee)
        }
        Log.v(name, "fillDatabase end")
    }

    private fun generateBloodType(): Int {
        when (Random.nextInt(0, 101)) {
            in 0..60 -> return 0
            in 61..90 -> return 1
            in 91..98 -> return 2
            else -> return 3
        }
    }

    fun clearDatabase() {
        employeeDao.deleteAll()
    }

}