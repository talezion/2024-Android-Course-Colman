package com.idz.lecture4_demo3.Modules.Students.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idz.lecture4_demo3.Model.Student
import com.idz.lecture4_demo3.Modules.Students.StudentsRcyclerViewActivity
import com.idz.lecture4_demo3.R

class StudentsRecyclerAdapter(var students: MutableList<Student>?): RecyclerView.Adapter<StudentViewHolder>() {

    var listener: StudentsRcyclerViewActivity.OnItemClickListener? = null

    override fun getItemCount(): Int = students?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_layout_row, parent, false)
        return StudentViewHolder(itemView, listener, students)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students?.get(position)
        holder.bind(student)
    }
}