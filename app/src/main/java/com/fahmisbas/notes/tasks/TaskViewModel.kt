package com.fahmisbas.notes.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahmisbas.notes.models.Task
import com.fahmisbas.notes.models.ToDo

class TaskViewModel : ViewModel(),TaskListViewContract {

    private val model : TaskModel = TaskModel()

    private val _taskListLiveData : MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData : LiveData<MutableList<Task>> = _taskListLiveData

    init {
        _taskListLiveData.postValue(model.getFakeData())
    }

    override fun onToDoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskListLiveData.value?.get(taskIndex)?.toDos?.get(todoIndex)?.isComplete = isComplete
    }
}