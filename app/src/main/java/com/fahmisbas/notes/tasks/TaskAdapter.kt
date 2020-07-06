package com.fahmisbas.notes.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fahmisbas.notes.R
import com.fahmisbas.notes.foundations.BaseRecyclerAdapter
import com.fahmisbas.notes.models.Task
import com.fahmisbas.notes.navigation.NavigationActivity
import com.fahmisbas.notes.views.TaskView
import kotlinx.android.synthetic.main.view_add_button.view.*

class TaskAdapter(
    taskList: MutableList<Task> = mutableListOf(),
    val touchActionDelegate: TasksListFragment.TouchActionDelegate,
    val dataActionDelegate: TaskListViewContract
) : BaseRecyclerAdapter<Task>(taskList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == TYPE_INFO) {
            TaskViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
            )
        } else {
            AddButtonViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.view_add_button, parent, false)
            )
        }

    inner class TaskViewHolder(view: View) : BaseViewHolder<Task>(view) {
        override fun onBind(data: Task, listIndex: Int) {
            (view as TaskView).initView(
                task = data,
                toDoCheckCallback = { toDoIndex, isChecked ->
                dataActionDelegate.onToDoUpdated(listIndex, toDoIndex, isChecked)
            }, deleteCallback = {
                    dataActionDelegate.onTaskDeleted(listIndex)
            })
        }
    }

    inner class AddButtonViewHolder(view: View) : BaseRecyclerAdapter.AddButtonViewHolder(view) {
        override fun onBind(data: Unit, listIndex: Int) {
            view.buttonText.text = view.context.getString(R.string.add_button_task)
            view.setOnClickListener {
                touchActionDelegate.onAddButtonClicked(NavigationActivity.FRAGMENT_VALUE_TASK)
            }
        }
    }
}