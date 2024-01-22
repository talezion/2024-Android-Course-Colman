package com.idz.lecture4_demo3.Modules.Students

import android.os.Bundle
import android.util.Log
import android.view.Display.Mode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idz.lecture4_demo3.Model.Model
import com.idz.lecture4_demo3.Model.Student
import com.idz.lecture4_demo3.Modules.Students.Adapter.StudentsRecyclerAdapter
import com.idz.lecture4_demo3.R
import com.idz.lecture4_demo3.databinding.FragmentStudentsBinding

class StudentsFragment : Fragment() {

    var studentsRcyclerView: RecyclerView? = null
    var adapter: StudentsRecyclerAdapter? = null
    var progressBar: ProgressBar? = null

    private var _binding: FragmentStudentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: StudentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStudentsBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this)[StudentsViewModel::class.java]

        progressBar = binding.progressBar

        progressBar?.visibility = View.VISIBLE

        viewModel.students = Model.instance.getAllStudents()

        studentsRcyclerView = binding.rvStudentsFragmentList
        studentsRcyclerView?.setHasFixedSize(true)
        studentsRcyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = StudentsRecyclerAdapter(viewModel.students?.value)
        adapter?.listener = object : StudentsRcyclerViewActivity.OnItemClickListener {

            override fun onItemClick(position: Int) {
                Log.i("TAG", "StudentsRecyclerAdapter: Position clicked $position")
                val student = viewModel.students?.value?.get(position)
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

        viewModel.students?.observe(viewLifecycleOwner) {
            adapter?.students = it
            adapter?.notifyDataSetChanged()
            progressBar?.visibility = View.GONE
        }

        binding.pullToRefresh.setOnRefreshListener {
            reloadData()
        }

        Model.instance.studentsListLoadingState.observe(viewLifecycleOwner) { state ->
             binding.pullToRefresh.isRefreshing = state == Model.LoadingState.LOADING
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        reloadData()
    }

    private fun reloadData() {
        progressBar?.visibility = View.VISIBLE
        Model.instance.refreshAllStudents()
        progressBar?.visibility = View.GONE
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}