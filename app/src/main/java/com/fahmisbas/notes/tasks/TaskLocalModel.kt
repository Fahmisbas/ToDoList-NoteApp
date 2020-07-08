package com.fahmisbas.notes.tasks

import com.fahmisbas.notes.application.NoteApplication
import com.fahmisbas.notes.database.RoomDatabaseClient
import com.fahmisbas.notes.models.Task
import com.fahmisbas.notes.models.ToDo
import com.fahmisbas.notes.notes.TIMEOUT_DURATION_MILIS
import kotlinx.coroutines.*
import javax.inject.Inject

class TaskLocalModel @Inject constructor() : ITaskModel {

    private var databaseClient: RoomDatabaseClient =
        RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)


    private suspend fun performOperationWithTimeout(
        function: () -> Unit,
        callback: SuccessCallbak
    ) {
        val job = GlobalScope.async {
            try {
                withTimeout(TIMEOUT_DURATION_MILIS) {
                    function.invoke()
                }
            } catch (e: Exception) {
                callback.invoke(false)
            }
        }
        job.await()
        callback.invoke(true)
    }

    override suspend fun addTask(task: Task, callback: SuccessCallbak) {
        val masterJob = GlobalScope.async {
            //add task entity component
            try {
                databaseClient.taskDao().addTask(task)
            } catch (e: Exception) {
                callback.invoke(false)
            }
            // add todos list component
            addTodosJob(task)
        }
        masterJob.await()
        callback.invoke(true)

    }

    override suspend fun updateTask(task: Task, callback: SuccessCallbak) {
        performOperationWithTimeout({ databaseClient.taskDao().updateTask(task) }, callback)

    }

    override suspend fun updateTodo(toDo: ToDo, callback: SuccessCallbak) {
        performOperationWithTimeout({ databaseClient.taskDao().updateTodo(toDo) }, callback)
    }

    override suspend fun deleteTask(task: Task, callback: SuccessCallbak) {
        performOperationWithTimeout({ databaseClient.taskDao().deleteTask(task) }, callback)
    }

    override fun retrieveTask(callback: (List<Task>?) -> Unit) {
        GlobalScope.launch {
            val job = async {
                withTimeoutOrNull(TIMEOUT_DURATION_MILIS) {
                    databaseClient.taskDao().retrieveTask()
                }
            }
            callback.invoke(job.await())
        }
    }

    private fun addTodosJob(task: Task): Job = GlobalScope.async {
        task.toDos.forEach {
            databaseClient.taskDao().addTodo(it)
        }
    }

}