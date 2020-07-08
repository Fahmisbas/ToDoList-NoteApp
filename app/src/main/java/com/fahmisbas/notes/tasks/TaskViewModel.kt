package com.fahmisbas.notes.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahmisbas.notes.foundations.ApplicationScope
import com.fahmisbas.notes.models.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class TaskViewModel : ViewModel(), TaskListViewContract {

    @Inject
    lateinit var localModel: ITaskModel

    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData

    init {
        Toothpick.inject(this, ApplicationScope.scope)
        loadData()
    }

    fun loadData() {
        localModel.retrieveTask { nullableList ->
            nullableList?.let {
                _taskListLiveData.postValue(it.toMutableList())
            }
        }
    }

    override fun onToDoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        GlobalScope.launch {
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
    }

    override fun onTaskDeleted(taskIndex: Int) {
        GlobalScope.launch {
            _taskListLiveData.value?.let {
                localModel.deleteTask(it[taskIndex]) {
                    loadData()
                }
            }
        }

    }
}
