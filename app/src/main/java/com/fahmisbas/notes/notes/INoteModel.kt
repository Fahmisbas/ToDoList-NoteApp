package com.fahmisbas.notes.notes

import com.fahmisbas.notes.models.Note

typealias SuccessCallbak = (Boolean) -> Unit
interface INoteModel {

    fun addNote(note : Note, callback : SuccessCallbak)
    fun updateNote(note : Note, callback : SuccessCallbak)
    fun deleteNote(note : Note, callback : SuccessCallbak)
    fun retrieveNotes() : List<Note>
    fun getFakeData() : MutableList<Note>
}