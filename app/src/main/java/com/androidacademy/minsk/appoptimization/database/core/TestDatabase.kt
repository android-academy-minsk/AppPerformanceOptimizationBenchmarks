package com.androidacademy.minsk.appoptimization.database.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidacademy.minsk.appoptimization.database.dao.EmployeeDao
import com.androidacademy.minsk.appoptimization.database.model.Employee


@Database(entities = [Employee::class], version = 8)
abstract class TestDatabase : RoomDatabase() {
    abstract val employeeDao: EmployeeDao
}
