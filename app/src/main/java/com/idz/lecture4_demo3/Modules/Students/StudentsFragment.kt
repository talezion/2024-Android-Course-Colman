package com.idz.lecture4_demo3.Modules.Students

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idz.lecture4_demo3.Model.Model
import com.idz.lecture4_demo3.Model.Student
import com.idz.lecture4_demo3.Modules.Students.Adapter.StudentsRecyclerAdapter
import com.idz.lecture4_demo3.R

class StudentsFragment : Fragment() {

    var studentsRcyclerView: RecyclerView? = null
    var students: MutableList<Student>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_students, container, false)

        students = Model.instance.students
        studentsRcyclerView = view.findViewById(R.id.rvStudentsFragmentList)
        studentsRcyclerView?.setHasFixedSize(true)
        studentsRcyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = StudentsRecyclerAdapter(students)
        adapter.listener = object : StudentsRcyclerViewActivity.OnItemClickListener {

            override fun onItemClick(position: Int) {
                Log.i("TAG", "StudentsRecyclerAdapter: Position clicked $position")
            }

            override fun onStudentClicked(student: Student?) {
                Log.i("TAG", "STUDENT $student")
            }
        }

        studentsRcyclerView?.adapter = adapter

        return view
    }
}