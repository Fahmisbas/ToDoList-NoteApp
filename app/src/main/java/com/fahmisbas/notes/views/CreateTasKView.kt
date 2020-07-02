package com.fahmisbas.notes.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.fahmisbas.notes.foundations.NullFieldChecker
import kotlinx.android.synthetic.main.view_create_task.view.*
import kotlinx.android.synthetic.main.view_create_todo.view.*

class CreateTasKView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : LinearLayout(context,attrs,defStyleAttr), NullFieldChecker {
    override fun hasNullField(): Boolean = taskEditText.editableText.isNullOrEmpty()

}