package com.fahmisbas.notes.notes

import android.util.Log
import com.fahmisbas.notes.application.NoteApplication
import com.fahmisbas.notes.database.RoomDatabaseClient
import com.fahmisbas.notes.models.Note
import javax.inject.Inject
import kotlin.math.log

class NoteLocalModel @Inject constructor() : INoteModel {

    private var databaseClient: RoomDatabaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    override fun getFakeData() : MutableList<Note>{
        return mutableListOf(
            Note("Test 1"),
            Note("Test 2"),
            Note("Test 3"),
            Note("Test 4")
        )
    }

    override fun addNote(note: Note, callback: SuccessCallbak) {
        callback.invoke(true)
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