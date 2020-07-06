package com.fahmisbas.notes.notes

import android.util.Log
import com.fahmisbas.notes.application.NoteApplication
import com.fahmisbas.notes.database.RoomDatabaseClient
import com.fahmisbas.notes.models.Note
import javax.inject.Inject
import kotlin.math.log

class NoteLocalModel @Inject constructor() : INoteModel {

    private var databaseClient: RoomDatabaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    override fun getFakeData() : MutableList<Note> = retrieveNotes().toMutableList()
//        return mutableListOf(
////            Note(description = "Test 1"),
////            Note(description ="Test 2"),
////            Note(description ="Test 3"),
////            Note(description ="Test 4")
//        )


    override fun addNote(note: Note, callback: SuccessCallbak) {
        databaseClient.noteDAO().addNote(note)
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: SuccessCallbak) {
        databaseClient.noteDAO().updateNote(note)
        callback.invoke(true)
    }

    override fun deleteNote(note: Note, callback: SuccessCallbak) {
        databaseClient.noteDAO().deleteNote(note)
        callback.invoke(true)
    }

    override fun retrieveNotes(): List<Note> = databaseClient.noteDAO().retrieveNotes()

}