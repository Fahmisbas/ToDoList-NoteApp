package com.fahmisbas.notes.models



data class Task @JvmOverloads constructor(
    var title: String,
    val toDos: MutableList<ToDo> = mutableListOf(),
    var tag: Tag? = null
)

data class ToDo(
    var description: String,
    var isComplete: Boolean = false
)

data class Note(
    var description: String,
    var tag: Tag? = null
)

data class Tag(
    val name: String,
    var colourResId: Int
)

