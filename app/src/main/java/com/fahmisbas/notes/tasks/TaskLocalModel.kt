package com.fahmisbas.notes.tasks

import android.util.Log
import com.fahmisbas.notes.application.NoteApplication
import com.fahmisbas.notes.database.RoomDatabaseClient
import com.fahmisbas.notes.models.Task
import com.fahmisbas.notes.models.ToDo
import javax.inject.Inject

class TaskLocalModel @Inject constructor() : ITaskModel{


    private var databaseClient: RoomDatabaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)


    override fun addTask(task: Task, callback: SuccessCallbak) {
        Log.i("udemy",task.toString())
        callback.invoke(true)
    }

    override fun updateTask(task: Task, callback: SuccessCallbak) {
        TODO("Not yet implemented")
    }

    override fun deleteTask(task: Task, callback: SuccessCallbak) {
        TODO("Not yet implemented")
    }

    override fun retrieveTask(): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getFakeData(): MutableList<Task> = (mutableListOf(
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