package com.idz.lecture4_demo3.Model

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object {
        val instance: Model = Model()
    }

}