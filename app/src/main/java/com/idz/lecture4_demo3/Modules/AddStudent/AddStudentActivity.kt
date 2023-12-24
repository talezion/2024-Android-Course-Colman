package com.idz.lecture4_demo3.Modules.AddStudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.idz.lecture4_demo3.R

class AddStudentActivity : AppCompatActivity() {

    var nameTextField: EditText? = null
    var idTextField: EditText? = null
    var messageTextView: TextView? = null
    var saveButton: Button? = null
    var cancelButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        setupUI()
    }

    private fun setupUI() {
        nameTextField = findViewById(R.id.etAddStudentName)
        idTextField = findViewById(R.id.etAddStudentID)
        messageTextView = findViewById(R.id.tvAddStudentSaved)
        saveButton = findViewById(R.id.btnAddStudentSave)
        cancelButton = findViewById(R.id.btnAddStudentCancel)

        cancelButton?.setOnClickListener {
            finish()
        }

        saveButton?.setOnClickListener {
            val name = nameTextField?.text.toString()
            messageTextView?.text = name
        }
    }
}