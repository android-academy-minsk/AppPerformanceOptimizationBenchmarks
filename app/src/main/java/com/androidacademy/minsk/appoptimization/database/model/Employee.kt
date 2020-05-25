package com.androidacademy.minsk.appoptimization.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = arrayOf(
        Index(value = ["age"], name = "index_age"),
        Index(value = ["sex"], name = "index_sex"),
        Index(value = ["bloodType"], name = "index_bloodType")
    )
)
class Employee {
    @PrimaryKey
    var id: Long? = null

    var firstName: String? = null

    @ColumnInfo(name = "last_name")
    var lastName: String? = null

    var age: Int? = null
    var ageNoIndex: Int? = null

    var sex: Int? = null
    var sexNoIndex: Int? = null

    var bloodType: Int? = null
    var bloodTypeNoIndex: Int? = null
}