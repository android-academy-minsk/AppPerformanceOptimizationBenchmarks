package com.androidacademy.minsk.appoptimization.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.androidacademy.minsk.appoptimization.database.model.Employee


@Dao
interface EmployeeDao {
    @Query("SELECT * FROM Employee WHERE sexNoIndex = :sex")
    fun getSexNoIndex(sex: Int): List<Employee>

    @Query("SELECT * FROM Employee WHERE sex = :sex")
    fun getSex(sex: Int): List<Employee>


    @Query("SELECT * FROM Employee WHERE age > :age")
    fun getByAge(age: Int): List<Employee>

    @Query("SELECT * FROM Employee WHERE last_name = :lastName")
    fun getByLastName(lastName: String): List<Employee>

    @Query("SELECT * FROM Employee WHERE ageNoIndex > :age")
    fun selectByAgeNoIndex(age: Int): List<Employee>

    @Query("SELECT * FROM Employee WHERE bloodType = :bloodType")
    fun findByBloodType(bloodType: Int): List<Employee>

    @Query("SELECT * FROM Employee WHERE bloodTypeNoIndex = :bloodType")
    fun findByNotIndexedBloodType(bloodType: Int): List<Employee>

    @Query("SELECT * FROM Employee")
    fun getAll(): List<Employee>

    @Query("SELECT * FROM Employee WHERE id = :id")
    fun getById(id: Long): Employee


    @Query("UPDATE Employee set age = :age WHERE id = :id")
    fun updateAge(id: Long, age: Int)

    @Query("UPDATE Employee set age = :age WHERE id = :id and age <> :age")
    fun updateAgeWithValueCheck(id: Long, age: Int)

    @Insert
    fun insert(employee: Employee)

    @Query("DELETE FROM Employee")
    fun deleteAll()

}
