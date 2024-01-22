package com.idz.lecture4_demo3.Model

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.os.HandlerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.idz.lecture4_demo3.dao.AppLocalDatabase
import java.util.concurrent.Executors

class Model private constructor() {

    enum class LoadingState {
        LOADING,
        LOADED
    }

    private val database = AppLocalDatabase.db
    private var executor = Executors.newSingleThreadExecutor()
    private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())
    private val firebaseModel = FirebaseModel()
    private val students: LiveData<MutableList<Student>>? = null
    val studentsListLoadingState: MutableLiveData<LoadingState> = MutableLiveData(LoadingState.LOADED)

    companion object {
        val instance: Model = Model()
    }

    interface GetAllStudentsListener {
        fun onComplete(students: List<Student>)
    }

    fun getAllStudents(): LiveData<MutableList<Student>> {
        refreshAllStudents()
        return students ?: database.studentDao().getAll()
    }

    fun refreshAllStudents() {

        studentsListLoadingState.value = LoadingState.LOADING

        // 1. Get last local update
        val lastUpdated: Long = Student.lastUpdated

        // 2. Get all updated records from firestore since last update locally
        firebaseModel.getAllStudents(lastUpdated) { list ->
            Log.i("TAG", "Firebase returned ${list.size}, lastUpdated: $lastUpdated")
            // 3. Insert new record to ROOM
            executor.execute {
                var time = lastUpdated
                for (student in list) {
                    database.studentDao().insert(student)

                    student.lastUpdated?.let {
                        if (time < it)
                            time = student.lastUpdated ?: System.currentTimeMillis()
                    }
                }

                // 4. Update local data
                Student.lastUpdated = time
                studentsListLoadingState.postValue(LoadingState.LOADED)
            }
        }
    }

    fun addStudent(student: Student, callback: () -> Unit) {
        firebaseModel.addStudent(student) {
            refreshAllStudents()
            callback()
        }
    }
}
