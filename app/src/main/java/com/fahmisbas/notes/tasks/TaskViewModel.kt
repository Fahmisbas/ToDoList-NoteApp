package com.fahmisbas.notes.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahmisbas.notes.foundations.ApplicationScope
import com.fahmisbas.notes.models.Task
import com.fahmisbas.notes.models.ToDo
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class TaskViewModel : ViewModel(),TaskListViewContract {

    @Inject
    lateinit var localModel : ITaskModel

    private val _taskListLiveData : MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData : LiveData<MutableList<Task>> = _taskListLiveData

    init{
        Toothpick.inject(this,ApplicationScope.scope)
        loadData()
    }

    fun loadData() {
        _taskListLiveData.postValue(localModel.getFakeData())
    }

    override fun onToDoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskListLiveData.value?.let {
            val todo = it[taskIndex].toDos[todoIndex]
            todo.apply {
                this.isComplete = isComplete
                this.taskId = it[taskIndex].uid
            }
            localModel.updateTodo(todo) {
                loadData()
            }
        }
    }

    override fun onTaskDeleted(taskIndex: Int) {
        _taskListLiveData.value?.let {
            localModel.deleteTask(it[taskIndex]) {
                loadData()
            }
        }
    }
}
