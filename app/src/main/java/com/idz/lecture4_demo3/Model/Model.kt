package com.idz.lecture4_demo3.Model

import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat
import com.idz.lecture4_demo3.dao.AppLocalDatabase
import java.util.concurrent.Executors

class Model private constructor() {

    private val database = AppLocalDatabase.db
    private var executor = Executors.newSingleThreadExecutor()
    private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())
    private val firebaseModel = FirebaseModel()

    companion object {
        val instance: Model = Model()
    }

    interface GetAllStudentsListener {
        fun onComplete(students: List<Student>)
    }

    fun getAllStudents(callback: (List<Student>) -> Unit) {
        firebaseModel.getAllStudents(callback)
//        executor.execute {
//
//            Thread.sleep(5000)
//
//            val students = database.studentDao().getAll()
//            mainHandler.post {
//                // Main Thread
//                callback(students)
//            }
//        }
    }

    fun addStudent(student: Student, callback: () -> Unit) {
        firebaseModel.addStudent(student, callback)
//        executor.execute {
//            database.studentDao().insert(student)
//            mainHandler.post {
//                callback()
//            }
//        }
    }
}
