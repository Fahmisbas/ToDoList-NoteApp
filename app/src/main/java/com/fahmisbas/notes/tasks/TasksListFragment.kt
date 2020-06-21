package com.fahmisbas.notes.tasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmisbas.notes.R
import com.fahmisbas.notes.models.Task
import com.fahmisbas.notes.models.ToDo
import kotlinx.android.synthetic.main.fragment_tasks_list.*

class TasksListFragment : Fragment() {

    lateinit var viewModel: TaskViewModel
    lateinit var contentView : TaskListView
    lateinit var touchActionDelegate: TasksListFragment.TouchActionDelegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if (it is TasksListFragment.TouchActionDelegate) {
                touchActionDelegate = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks_list, container, false).apply {
            contentView = this as TaskListView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        setContentView()
    }

    private fun setContentView() {
        contentView.initView(touchActionDelegate,viewModel)
    }

    private fun bindViewModel() {
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        viewModel.taskListLiveData.observe(viewLifecycleOwner, Observer { taskList ->
            contentView.updateList(taskList)
        })
    }

    companion object {
        fun newInstance() = TasksListFragment()
    }
    
    interface TouchActionDelegate {
        fun onAddButtonClicked(value : String)
    }
}