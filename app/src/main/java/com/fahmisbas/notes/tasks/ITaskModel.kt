package com.fahmisbas.notes.tasks

import com.fahmisbas.notes.models.Task


typealias SuccessCallbak = (Boolean) -> Unit
interface ITaskModel {
    fun addTask(task : Task, callback : SuccessCallbak)
    fun updateTask(task : Task, callback : SuccessCallbak)
    fun deleteTask(task : Task, callback : SuccessCallbak)
    fun retrieveTask() : List<Task>
    fun getFakeData(): MutableList<Task>
}