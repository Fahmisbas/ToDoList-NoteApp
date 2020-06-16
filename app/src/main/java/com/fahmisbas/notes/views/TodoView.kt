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


    fun initViews(toDo: ToDo) {
        descriptionView.text = toDo.description
        completeCheckBox.isChecked = toDo.isComplete
        if (toDo.isComplete) {
            createStrikeThrough()
        }
        setupCheckStateListener()
    }

    private fun setupCheckStateListener() {
        completeCheckBox!!.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                createStrikeThrough()
            } else {
                removeStrikeThrough()
            }
        }
    }

    private fun createStrikeThrough() {
        descriptionView.apply {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun removeStrikeThrough() {
        descriptionView.apply {
            paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}