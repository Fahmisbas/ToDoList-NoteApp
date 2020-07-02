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

    init {
        val taskViewModelScope = Toothpick.openScopes(ApplicationScope.scope,this)
        taskViewModelScope.installModules(Module().apply {
            bind(ITaskModel::class.java).toInstance(TaskModel())
        })
        Toothpick.inject(this,taskViewModelScope)
        _taskListLiveData.postValue(localModel.getFakeData())
    }

    override fun onToDoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskListLiveData.value?.get(taskIndex)?.toDos?.get(todoIndex)?.isComplete = isComplete
    }
}

class TaskModel() : ITaskModel {
    override fun addTask(task: Task, callback: SuccessCallbak) {
        TODO("Not yet implemented")
    }

    override fun updateTask(task: Task, callback: SuccessCallbak) {
        TODO("Not yet implemented")
    }

    override fun deleteTask(task: Task, callback: SuccessCallbak) {
        TODO("Not yet implemented")
    }

    override fun retrieveTask(): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getFakeData(): MutableList<Task> = mutableListOf(Task("Test Module Task",
        mutableListOf(ToDo("wadidaw"))))

}