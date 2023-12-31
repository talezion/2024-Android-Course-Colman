package com.idz.lecture4_demo3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class BlueFragment : Fragment() {

    private var textView: TextView? = null
    private var title: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blue, container, false)

        val blueTitle = arguments?.let { BlueFragmentArgs.fromBundle(it).TITLE }

        textView = view.findViewById(R.id.tvBlueFragmentTitle)
        textView?.text = blueTitle ?: "Please assign a title"


        val backButton: Button = view.findViewById(R.id.btnBlueFragmentBack)
        backButton.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        return view
    }
}
