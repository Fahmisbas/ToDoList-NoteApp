package com.fahmisbas.notes.notes

import com.fahmisbas.notes.models.Note

interface NoteListViewContract {
    fun onDeleteNote(note : Note)
}