package com.fahmisbas.notes.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fahmisbas.notes.R
import com.fahmisbas.notes.foundations.BaseRecyclerAdapter
import com.fahmisbas.notes.models.Task
import kotlinx.android.synthetic.main.item_task.view.*
import kotlinx.android.synthetic.main.view_todo.view.*

class TaskAdapter(
     taskList : MutableList<Task> = mutableListOf()
) : BaseRecyclerAdapter<Task>(taskList){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false))

    class ViewHolder(view : View) : BaseViewHolder<Task>(view) {
        override fun onBind(data : Task) {
            view.titleView.text = data.title
            data.toDos.forEach {todo ->
                val todoView = LayoutInflater.from(view.context).inflate(R.layout.view_todo, view.todoContainer,false).apply {
                    descriptionView.text = todo.description
                    completeCheckBox.isChecked = todo.isComplete
                }
                view.todoContainer.addView(todoView)
            }
        }
    }
}