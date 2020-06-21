package com.fahmisbas.notes.notes

import com.fahmisbas.notes.models.Note

class NoteModel {

    fun getFakeData() : MutableList<Note>{
        return mutableListOf(
            Note("Test 1"),
            Note("Test 2"),
            Note("Test 3"),
            Note("Test 4")
        )
    }
}