package com.fahmisbas.notes.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.fahmisbas.notes.models.ToDo
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context,attrs,defStyleAttr) {


    fun initViews(toDo: ToDo, callback: ((Boolean) -> Unit)? = null) {
        descriptionView.text = toDo.description
        completeCheckBox.isChecked = toDo.isComplete
        if (toDo.isComplete) {
            descriptionView.setStrikeThrough()
        }
        setupCheckStateListener(toDo, callback)
    }

    private fun setupCheckStateListener(todo : ToDo, callback : ((Boolean) -> Unit)? = null) {
        completeCheckBox!!.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            todo.isComplete = isChecked
            callback?.invoke(isChecked)
            if (isChecked) {
                descriptionView.setStrikeThrough()
            } else {
                descriptionView.removeStrikeThrough()
            }
        }
    }

}