package com.idz.lecture4_demo3.Modules.Students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idz.lecture4_demo3.Model.Model
import com.idz.lecture4_demo3.Model.Student
import com.idz.lecture4_demo3.Modules.Students.Adapter.StudentsRecyclerAdapter
import com.idz.lecture4_demo3.R
import com.idz.lecture4_demo3.databinding.ActivityStudentsRcyclerViewBinding

class StudentsRcyclerViewActivity : AppCompatActivity() {

    var studentsRcyclerView: RecyclerView? = null
    var students: List<Student>? = null
    var adapter: StudentsRecyclerAdapter? = null

    private lateinit var binding: ActivityStudentsRcyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStudentsRcyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Model.instance.getAllStudents { students ->
            this.students = students
            adapter?.students = students
            adapter?.notifyDataSetChanged()
        }

        studentsRcyclerView = binding.rvStudentREcyclerList
        studentsRcyclerView?.setHasFixedSize(true)
        studentsRcyclerView?.layoutManager = LinearLayoutManager(this)

        adapter = StudentsRecyclerAdapter(students)
        adapter?.listener = object : OnItemClickListener {

            override fun onItemClick(position: Int) {
                Log.i("TAG", "StudentsRecyclerAdapter: Position clicked $position")
            }

            override fun onStudentClicked(student: Student?) {
                Log.i("TAG", "STUDENT $student")
            }
        }

        studentsRcyclerView?.adapter = adapter
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int) // Student
        fun onStudentClicked(student: Student?)
    }

    override fun onResume() {
        super.onResume()

        Model.instance.getAllStudents { students ->
            this.students = students
            adapter?.students = students
            adapter?.notifyDataSetChanged()
        }
    }
}