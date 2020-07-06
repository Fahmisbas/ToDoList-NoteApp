package com.fahmisbas.notes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahmisbas.notes.foundations.ApplicationScope
import com.fahmisbas.notes.models.Note
import toothpick.Toothpick
import javax.inject.Inject

class NoteViewModel : ViewModel(),NoteListViewContract {

    @Inject
    lateinit var model : INoteModel

    private val _noteListLiveData : MutableLiveData<List<Note>> = MutableLiveData()
    val noteListLiveData : LiveData<List<Note>> = _noteListLiveData

    init {
        Toothpick.inject(this,ApplicationScope.scope)
        loadData()
    }

    fun loadData() {
        _noteListLiveData.postValue(model.retrieveNotes())
    }

    override fun onDeleteNote(note: Note) {
        model.deleteNote(note) {
            if (it) {
                loadData()
            }
        }
    }


}