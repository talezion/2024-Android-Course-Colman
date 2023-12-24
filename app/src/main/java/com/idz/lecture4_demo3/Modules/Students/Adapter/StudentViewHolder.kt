package com.idz.lecture4_demo3.Modules.Students.Adapter

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.idz.lecture4_demo3.Model.Student
import com.idz.lecture4_demo3.Modules.Students.StudentsRcyclerViewActivity
import com.idz.lecture4_demo3.R

class StudentViewHolder(val itemView: View,
                        val listener: StudentsRcyclerViewActivity.OnItemClickListener?,
                        var students: MutableList<Student>?): RecyclerView.ViewHolder(itemView) {

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