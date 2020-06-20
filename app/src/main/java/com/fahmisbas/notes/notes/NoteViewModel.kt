package com.fahmisbas.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahmisbas.notes.models.Note

class NoteViewModel : ViewModel(),NoteListViewContract {

    private val _noteListLiveData : MutableLiveData<List<Note>> = MutableLiveData()
    val noteListLiveData : LiveData<List<Note>> = _noteListLiveData

    init {
        _noteListLiveData.postValue(getFakeData())
    }

    fun getFakeData() : MutableList<Note>{
        return mutableListOf(Note("Test 1"),
            Note("Test 2"),
            Note("Test 3"),
            Note("Test 4")
        )
    }
}