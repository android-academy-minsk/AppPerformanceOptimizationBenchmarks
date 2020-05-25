package com.androidacademy.minsk.appoptimization.di

import android.content.Context
import androidx.room.Room
import com.androidacademy.minsk.appoptimization.database.core.TestDatabase
import com.androidacademy.minsk.appoptimization.database.dao.EmployeeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(context: Context) {
    private val database: TestDatabase

    init {
        database = Room.databaseBuilder(context, TestDatabase::class.java, "test_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    internal fun providesRoomDatabase(): TestDatabase {
        return database
    }

    @Singleton
    @Provides
    internal fun providesEmployeeDao(database: TestDatabase): EmployeeDao {
        return database.employeeDao
    }

}
