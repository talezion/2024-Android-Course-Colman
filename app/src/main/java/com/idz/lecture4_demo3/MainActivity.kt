package com.idz.lecture4_demo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val addStudentButton = findViewById<Button>(R.id.btnMainAddStudent)
        val addStudentButton: Button = findViewById(R.id.btnMainAddStudent)

        // Option 1
//        class ButtonOnClickListener : View.OnClickListener {
//            override fun onClick(v: View?) {
//
//                val intent = Intent(this@MainActivity, AddStudentActivity::class.java)
//            }
//
//        }

//        val listener = ButtonOnClickListener()
//        addStudentButton.setOnClickListener(ButtonOnClickListener()) //(listener)

//        addStudentButton.setOnClickListener {
//
//        }

        addStudentButton.setOnClickListener(::onAddStudentButtonClicked)
    }

    fun onAddStudentButtonClicked(view: View) {
        val intent = Intent(this, AddStudentActivity::class.java)
        startActivity(intent)
    }
}