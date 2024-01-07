package com.idz.lecture4_demo3.Modules.AddStudent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.idz.lecture4_demo3.Model.Model
import com.idz.lecture4_demo3.Model.Student
import com.idz.lecture4_demo3.R

class AddStudentFragment : Fragment() {

    private var nameTextField: EditText? = null
    private var idTextField: EditText? = null
    private var messageTextView: TextView? = null
    private var saveButton: Button? = null
    private var cancelButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)
        setupUI(view)
        return view
    }

    private fun setupUI(view: View) {
        nameTextField = view.findViewById(R.id.etAddStudentName)
        idTextField = view.findViewById(R.id.etAddStudentID)
        messageTextView = view.findViewById(R.id.tvAddStudentSaved)
        saveButton = view.findViewById(R.id.btnAddStudentSave)
        cancelButton = view.findViewById(R.id.btnAddStudentCancel)
        messageTextView?.text = ""

        cancelButton?.setOnClickListener {
            Navigation.findNavController(it).popBackStack(R.id.studentsFragment, false)
        }

        saveButton?.setOnClickListener {
            val name = nameTextField?.text.toString()
            val id = idTextField?.text.toString()

            val student = Student(name, id, "", false)
            Model.instance.addStudent(student) {
                Navigation.findNavController(it).popBackStack(R.id.studentsFragment, false)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}