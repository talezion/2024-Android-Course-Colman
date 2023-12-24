package com.idz.lecture4_demo3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    var fragmentOne: BlueFragment? = null
    var fragmentTwo: BlueFragment? = null
    var fragmentThree: BlueFragment? = null
    var fragmentFour: BlueFragment? = null

    var buttonOne: Button? = null
    var buttonTwo: Button? = null
    var buttonThree: Button? = null
    var buttonFour: Button? = null

    var inDisplayFragment: BlueFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Create fragment instances
        // 2. Set buttons references

        fragmentOne = BlueFragment.newInstance("One")
        fragmentTwo = BlueFragment.newInstance("Two")
        fragmentThree = BlueFragment.newInstance("Three")
        fragmentFour = BlueFragment.newInstance("Four")

        buttonOne = findViewById(R.id.btnMainTabOne)
        buttonTwo = findViewById(R.id.btnMainTabTwo)
        buttonThree = findViewById(R.id.btnMainTabThree)
        buttonFour = findViewById(R.id.btnMainTabFour)

        buttonOne?.setOnClickListener {
            fragmentOne?.let {
                displayBlueFragment(it)
            }
        }

        buttonTwo?.setOnClickListener {
            fragmentTwo?.let {
                displayBlueFragment(it)
            }
        }

        buttonThree?.setOnClickListener {
            fragmentThree?.let {
                displayBlueFragment(it)
            }
        }

        buttonFour?.setOnClickListener {
            fragmentFour?.let {
                displayBlueFragment(it)
            }
        }

        fragmentOne?.let {
            displayBlueFragment(it)
        }
    }

    fun displayBlueFragment(fragment: BlueFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.flMainFragment, fragment)

        inDisplayFragment?.let {
            transaction.remove(it)
        }

        transaction.addToBackStack("TAG")
        transaction.commit()
        inDisplayFragment = fragment
    }

//    fun removeBlueFragment() {
//        blueFragment?.let {
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.remove(it)
//            transaction.addToBackStack("TAG")
//            transaction.commit()
//        }
//        blueFragment = null
//    }
}