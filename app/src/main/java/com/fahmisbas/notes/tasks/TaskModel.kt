package com.fahmisbas.notes.tasks

import com.fahmisbas.notes.models.Task
import com.fahmisbas.notes.models.ToDo

class TaskModel {

    fun getFakeData(): MutableList<Task> = (mutableListOf(
        Task(
            "Testing One", mutableListOf(
                ToDo("Test One", true),
                ToDo("Test Two")
            )
        ),
        Task("Testing Two"),
        Task("Testing Three", mutableListOf(
            ToDo("Test A"),
            ToDo("Test B")
        ))
    ))
}