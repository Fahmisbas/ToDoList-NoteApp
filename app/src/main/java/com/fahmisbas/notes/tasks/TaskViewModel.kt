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

    private val _stateChangeLiveData: MutableLiveData<ItemState> = MutableLiveData()
    val stateChangeLiveData: LiveData<ItemState> = _stateChangeLiveData

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
            _taskListLiveData.value?.let { taskList ->
                val todo = taskList[taskIndex].toDos[todoIndex]
                todo.apply {
                    this.isComplete = isComplete
                    this.taskId = taskList[taskIndex].uid
                }
                localModel.updateTodo(todo) { success ->
                    if (success) {
                        _stateChangeLiveData.postValue(
                            ItemState.ItemUpdated(
                                newTask = taskList[taskIndex],
                                il = taskIndex,
                                iv = taskIndex + 1
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onTaskDeleted(taskIndex: Int) {
        GlobalScope.launch {
            _taskListLiveData.value?.let { taskList ->
                localModel.deleteTask(taskList[taskIndex]) { success ->
                    if (success) {
                        _stateChangeLiveData.postValue(
                            ItemState.ItemDeleted(
                                il = taskIndex,
                                iv = taskIndex + 1
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class ItemState(val indexInList: Int, val indexInView: Int) {
        class ItemUpdated(val newTask: Task, il: Int, iv: Int) : ItemState(il, iv)
        class ItemDeleted(il: Int, iv: Int) : ItemState(il, iv)
    }
}
