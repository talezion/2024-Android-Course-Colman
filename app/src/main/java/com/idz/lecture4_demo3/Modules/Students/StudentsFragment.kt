package com.idz.lecture4_demo3.Modules.Students

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idz.lecture4_demo3.Model.Model
import com.idz.lecture4_demo3.Model.Student
import com.idz.lecture4_demo3.Modules.Students.Adapter.StudentsRecyclerAdapter
import com.idz.lecture4_demo3.R

class StudentsFragment : Fragment() {

    var studentsRcyclerView: RecyclerView? = null
    var students: List<Student>? = null
    var adapter: StudentsRecyclerAdapter? = null
    var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_students, container, false)
        progressBar = view.findViewById(R.id.progressBar)

        progressBar?.visibility = View.VISIBLE

        Model.instance.getAllStudents { students ->
            this.students = students
            adapter?.students = students
            adapter?.notifyDataSetChanged()

            progressBar?.visibility = View.GONE
        }

        studentsRcyclerView = view.findViewById(R.id.rvStudentsFragmentList)
        studentsRcyclerView?.setHasFixedSize(true)
        studentsRcyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = StudentsRecyclerAdapter(students)
        adapter?.listener = object : StudentsRcyclerViewActivity.OnItemClickListener {

            override fun onItemClick(position: Int) {
                Log.i("TAG", "StudentsRecyclerAdapter: Position clicked $position")
                val student = students?.get(position)
                student?.let {
                    val action = StudentsFragmentDirections.actionStudentsFragmentToBlueFragment(it.name)
                    Navigation.findNavController(view).navigate(action)
                }
            }

            override fun onStudentClicked(student: Student?) {
                Log.i("TAG", "STUDENT $student")
            }
        }

        studentsRcyclerView?.adapter = adapter

        val addStudentButton: ImageButton = view.findViewById(R.id.ibtnStudentsFragmentAddStudent)
        val action = Navigation.createNavigateOnClickListener(StudentsFragmentDirections.actionGlobalAddStudentFragment())
        addStudentButton.setOnClickListener(action)

        return view
    }

    override fun onResume() {
        super.onResume()

        progressBar?.visibility = View.VISIBLE

        Model.instance.getAllStudents { students ->
            this.students = students
            adapter?.students = students
            adapter?.notifyDataSetChanged()

            progressBar?.visibility = View.GONE
        }
    }
}