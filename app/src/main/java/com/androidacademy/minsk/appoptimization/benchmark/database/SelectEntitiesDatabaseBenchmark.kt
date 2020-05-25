package com.androidacademy.minsk.appoptimization.benchmark.database

import android.content.Context
import com.androidacademy.minsk.appoptimization.benchmark.core.Benchmark
import com.androidacademy.minsk.appoptimization.database.core.TestDatabase
import com.androidacademy.minsk.appoptimization.database.dao.EmployeeDao
import kotlinx.coroutines.CoroutineScope

open class SelectEntitiesDatabaseBenchmark(
    context: Context,
    uiScope: CoroutineScope,
    database: dagger.Lazy<TestDatabase>,
    employeeDao: EmployeeDao
) : BaseDatabaseBenchmarkGroup(
    "Select Entities  DB test",
    context, uiScope, database, employeeDao
) {

    companion object {
        private const val BLOOD_TYPE_CONT = 4
    }

    init {

        benchmarks.add(object :
            Benchmark(
                "Select Entities test 1: select by not indexed 'sex' column ",
                context,
                uiScope
            ) {
            override fun benchmark() {
                val result = employeeDao.getSexNoIndex(1)
            }
        })
        benchmarks.add(object :
            Benchmark("Select Entities test 2: select by INDEXED 'sex' column ", context, uiScope) {
            override fun benchmark() {
                val result = employeeDao.getSex(1)
            }
        })
        benchmarks.add(object :
            Benchmark(
                "Select Entities test 3: select by not indexed bloodType column",
                context,
                uiScope
            ) {
            override fun benchmark() {
                for (i in 0..BLOOD_TYPE_CONT) {
                    val result = employeeDao.findByNotIndexedBloodType(i)
                }
            }
        })
        benchmarks.add(object :
            Benchmark(
                "Select Entities test 4: select by INDEXED bloodType column",
                context,
                uiScope
            ) {
            override fun benchmark() {
                for (i in 0..BLOOD_TYPE_CONT) {
                    val result = employeeDao.findByBloodType(i)
                }
            }
        })
    }


}