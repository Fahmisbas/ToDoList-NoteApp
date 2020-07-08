package com.fahmisbas.notes.tasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fahmisbas.notes.R

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

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    private fun setContentView() {
        contentView.initView(touchActionDelegate,viewModel)
    }

    private fun bindViewModel() {
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        viewModel.taskListLiveData.observe(viewLifecycleOwner, Observer { taskList ->
            contentView.updateList(taskList)
        })

        viewModel.stateChangeLiveData.observe(viewLifecycleOwner, Observer {itemState ->
            when(itemState) {
                is TaskViewModel.ItemState.ItemUpdated -> contentView.updateItem(
                    itemState.newTask,itemState.indexInList,itemState.indexInView)
                is TaskViewModel.ItemState.ItemDeleted -> contentView.deleteItem(
                    itemState.indexInList, itemState.indexInView)
            }
        })

    }

    companion object {
        fun newInstance() = TasksListFragment()
    }
    
    interface TouchActionDelegate {
        fun onAddButtonClicked(value : String)
    }
}