package com.fahmisbas.notes.tasks

import com.fahmisbas.notes.models.Task
import com.fahmisbas.notes.models.ToDo


typealias SuccessCallbak = (Boolean) -> Unit
interface ITaskModel {
    suspend fun addTask(task : Task, callback : SuccessCallbak)
    suspend fun updateTask(task : Task, callback : SuccessCallbak)
    suspend fun updateTodo(toDo: ToDo,callback: SuccessCallbak)
    suspend fun deleteTask(task : Task, callback : SuccessCallbak)
    fun retrieveTask(callback : (List<Task>?) -> Unit)
}