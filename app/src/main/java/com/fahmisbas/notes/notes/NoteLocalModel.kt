package com.fahmisbas.notes.notes

import com.fahmisbas.notes.models.Note
import javax.inject.Inject

class NoteLocalModel @Inject constructor() : INoteModel {

    override fun getFakeData() : MutableList<Note>{
        return mutableListOf(
            Note("Test 1"),
            Note("Test 2"),
            Note("Test 3"),
            Note("Test 4")
        )
    }

    override fun addNote(note: Note, callback: SuccessCallbak) {
        TODO("Not yet implemented")
    }

    override fun updateNote(note: Note, callback: SuccessCallbak) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(note: Note, callback: SuccessCallbak) {
        TODO("Not yet implemented")
    }

    override fun retrieveNotes(): List<Note> {
        TODO("Not yet implemented")
    }
}