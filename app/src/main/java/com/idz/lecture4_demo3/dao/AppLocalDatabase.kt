package com.idz.lecture4_demo3.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.idz.lecture4_demo3.Model.Student
import com.idz.lecture4_demo3.base.MyApplication

@Database(entities = [Student::class], version = 3)
abstract class AppLocalDbRepository : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}

object AppLocalDatabase {

    val db: AppLocalDbRepository by lazy {

        val context = MyApplication.Globals.appContext
            ?: throw IllegalStateException("Application context not available")

        Room.databaseBuilder(
            context,
            AppLocalDbRepository::class.java,
            "dbFileName.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}