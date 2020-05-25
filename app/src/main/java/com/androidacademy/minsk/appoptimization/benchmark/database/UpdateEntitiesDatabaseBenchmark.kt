package com.androidacademy.minsk.appoptimization.benchmark.database

import android.content.Context
import android.util.Log
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import com.androidacademy.minsk.appoptimization.database.core.TestDatabase
import com.androidacademy.minsk.appoptimization.database.dao.EmployeeDao
import kotlinx.coroutines.CoroutineScope

open class UpdateEntitiesDatabaseBenchmark(
    context: Context,
    uiScope: CoroutineScope,
    database: dagger.Lazy<TestDatabase>,
    employeeDao: EmployeeDao
) : BaseDatabaseBenchmarkGroup("Update Entities DB test", context, uiScope, database, employeeDao) {


    init {
        benchmarks.add(object :
            Benchmark(
                "Update Entities test 1: insert without value change check",
                context,
                uiScope
            ) {
            override fun benchmark() {
                val employee = employeeDao.getById(1000)
                updateEmployeeAge(employee.id!!, employee.age!!)
            }
        })

        benchmarks.add(object :
            Benchmark("Update Entities test 2: check updated value", context, uiScope) {
            override fun benchmark() {
                val employee = employeeDao.getById(1000)
                updateEmployeeAgeWithValueCheck(employee.id!!, employee.age!!)
            }
        })
    }


    private fun updateEmployeeAge(employeeId: Long, age: Int) {
        for (i in 0..100_000) {
            employeeDao.updateAge(employeeId, age)

            if (i % 1000 == 0) {
                Log.v(name, "updateEmployeeAge i = $i")
            }
        }
    }

    private fun updateEmployeeAgeWithValueCheck(employeeId: Long, age: Int) {
        for (i in 0..1000) {
            employeeDao.updateAgeWithValueCheck(employeeId, age)
        }
    }
}