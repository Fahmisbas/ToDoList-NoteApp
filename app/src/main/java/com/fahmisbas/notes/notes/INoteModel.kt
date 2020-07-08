package com.fahmisbas.notes.notes

import com.fahmisbas.notes.models.Note

typealias SuccessCallbak = (Boolean) -> Unit
interface INoteModel {

    suspend fun addNote(note : Note, callback : SuccessCallbak)
    suspend fun updateNote(note : Note, callback : SuccessCallbak)
    suspend fun deleteNote(note : Note, callback : SuccessCallbak)
    suspend fun retrieveNotes(callback : (List<Note>?) -> Unit)
}