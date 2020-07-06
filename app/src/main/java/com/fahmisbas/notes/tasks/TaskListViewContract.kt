package com.fahmisbas.notes.tasks

interface TaskListViewContract {
    fun onToDoUpdated(taskIndex : Int,todoIndex : Int, isComplete : Boolean)
    fun onTaskDeleted(taskIndex: Int)
}