package com.androidacademy.minsk.appoptimization.benchmark.database

import android.content.Context
import com.androidacademy.minsk.appoptimization.benchmark.core.BenchmarkGroup
import com.androidacademy.minsk.appoptimization.database.core.TestDatabase
import com.androidacademy.minsk.appoptimization.database.dao.EmployeeDao
import kotlinx.coroutines.CoroutineScope

open class BaseDatabaseBenchmarkGroup(
    name: String,
    context: Context,
    uiScope: CoroutineScope,
    protected val database: dagger.Lazy<TestDatabase>,
    protected val employeeDao: EmployeeDao
) : BenchmarkGroup(name, context, uiScope)