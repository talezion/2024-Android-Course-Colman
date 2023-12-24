package com.idz.lecture4_demo3

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

class StudentsRcyclerViewActivity : AppCompatActivity() {

    var studentsRcyclerView: RecyclerView? = null
    var students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_rcycler_view)

        students = Model.instance.students

        studentsRcyclerView = findViewById(R.id.rvStudentREcyclerList)
        studentsRcyclerView?.setHasFixedSize(true)

        // Set the layout manager
        studentsRcyclerView?.layoutManager = LinearLayoutManager(this)

        // Set the adapter
//        studentsRcyclerView?.adapter = StudentsRecyclerAdapter()

        /*
        1. On Item Click
        2. Clickable Background - DONE
         */

        val adapter = StudentsRecyclerAdapter()
        adapter.listener = object : OnItemClickListener {

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

    inner class StudentViewHolder(val itemView: View, val listener: OnItemClickListener?): RecyclerView.ViewHolder(itemView) {

        var nameTextView: TextView? = null
        var idTextView: TextView? = null
        var studentCheckbox: CheckBox? = null
        var student: Student? = null

        init {
            nameTextView = itemView.findViewById(R.id.tvStudentListRowName)
            idTextView = itemView.findViewById(R.id.tvStudentListRowID)
            studentCheckbox = itemView.findViewById(R.id.cbStudentListRow)

            studentCheckbox?.setOnClickListener {
                val student = students?.get(adapterPosition)
                student?.isChecked = studentCheckbox?.isChecked ?: false
            }

            itemView.setOnClickListener {
                Log.i("TAG", "StudentViewHolder: Position clicked $adapterPosition")

                listener?.onItemClick(adapterPosition)
                listener?.onStudentClicked(student)
            }
        }

        fun bind(student: Student?) {
            this.student = student
            nameTextView?.text = student?.name
            idTextView?.text = student?.id
            studentCheckbox?.apply {
                isChecked = student?.isChecked ?: false
            }
        }
    }

    inner class StudentsRecyclerAdapter: RecyclerView.Adapter<StudentViewHolder>() {

        var listener: OnItemClickListener? = null

        override fun getItemCount(): Int = students?.size ?: 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_layout_row, parent, false)
            return StudentViewHolder(itemView, listener)
        }

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            val student = students?.get(position)
            holder.bind(student)
        }
    }
}