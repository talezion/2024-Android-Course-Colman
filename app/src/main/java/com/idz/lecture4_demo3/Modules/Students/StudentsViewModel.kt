package com.idz.lecture4_demo3.Modules.Students

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.idz.lecture4_demo3.Model.Student

class StudentsViewModel: ViewModel() {
    var students: LiveData<MutableList<Student>>? = null
}