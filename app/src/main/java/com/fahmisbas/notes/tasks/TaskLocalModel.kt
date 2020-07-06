package com.fahmisbas.notes.tasks

import android.util.Log
import com.fahmisbas.notes.application.NoteApplication
import com.fahmisbas.notes.database.RoomDatabaseClient
import com.fahmisbas.notes.models.Task
import com.fahmisbas.notes.models.ToDo
import javax.inject.Inject

class TaskLocalModel @Inject constructor() : ITaskModel{


    private var databaseClient: RoomDatabaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    override fun getFakeData(): MutableList<Task> = retrieveTask().toMutableList()

    override fun addTask(task: Task, callback: SuccessCallbak) {
        databaseClient.taskDao().addTask(task)
        addTodosInTask(task)
        callback.invoke(true)
    }

    override fun updateTask(task: Task, callback: SuccessCallbak) {
        databaseClient.taskDao().updateTask(task)
        callback.invoke(true)
    }

    override fun updateTodo(toDo: ToDo, callback: SuccessCallbak) {
        databaseClient.taskDao().updateTodo(toDo)
        callback.invoke(true)
    }

    override fun deleteTask(task: Task, callback: SuccessCallbak) {
        databaseClient.taskDao().deleteTask(task)
        callback.invoke(true)
    }

    private fun addTodosInTask(task : Task) {
        task.toDos.forEach {todo ->
            databaseClient.taskDao().addTodo(todo)
        }
    }

    override fun retrieveTask(): List<Task> = databaseClient.taskDao().retrieveTask()


}