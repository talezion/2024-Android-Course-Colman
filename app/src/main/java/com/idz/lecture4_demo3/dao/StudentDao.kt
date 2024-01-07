package com.idz.lecture4_demo3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idz.lecture4_demo3.Model.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg students: Student)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM Student WHERE id =:id")
    fun getStudentById(id: String): Student
}