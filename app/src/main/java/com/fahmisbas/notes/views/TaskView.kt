package com.fahmisbas.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.fahmisbas.notes.R
import com.fahmisbas.notes.models.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var task: Task

    fun initView(task: Task, toDoCheckCallback : (Int,Boolean) -> Unit) {
        this.task = task
        titleView.text = task.title
        task.toDos.forEachIndexed { toDoIndex, toDo ->
            val todoView = (LayoutInflater.from(context).inflate(R.layout.view_todo, todoContainer, false) as TodoView).apply {
                initViews(toDo) {isChecked ->
                    toDoCheckCallback.invoke(toDoIndex,isChecked)
                    if (isTaskComplete()) {
                        this@TaskView.titleView.setStrikeThrough()
                    } else {
                        this@TaskView.titleView.removeStrikeThrough()
                    }
                }
            }
            todoContainer.addView(todoView)
        }
    }

    private fun isTaskComplete(): Boolean = task.toDos.filter { !it.isComplete }.isEmpty()
}