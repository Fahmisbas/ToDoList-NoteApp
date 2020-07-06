package com.fahmisbas.notes.database

import androidx.room.*
import com.fahmisbas.notes.models.Task
import com.fahmisbas.notes.models.TaskEntity
import com.fahmisbas.notes.models.ToDo

@Dao
interface TaskDAO {

    @Insert
    fun addTask(taskEntity: TaskEntity)

    @Insert
    fun addTodo(toDo: ToDo)

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Update
    fun updateTodo(toDo: ToDo)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun retrieveTask() : MutableList<Task>
}