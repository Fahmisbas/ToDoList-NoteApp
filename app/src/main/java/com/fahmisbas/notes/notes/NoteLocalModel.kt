package com.fahmisbas.notes.notes

import android.util.Log
import com.fahmisbas.notes.application.NoteApplication
import com.fahmisbas.notes.database.RoomDatabaseClient
import com.fahmisbas.notes.models.Note
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.math.log

const val TIMEOUT_DURATION_MILIS = 3000L

class NoteLocalModel @Inject constructor() : INoteModel {

    private var databaseClient: RoomDatabaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    private fun performOperationWithTimeout(function: () -> Unit, callback: SuccessCallbak) {
        GlobalScope.launch {
            val job = async {
                try {
                    withTimeout(TIMEOUT_DURATION_MILIS) {
                        function.invoke()
                    }
                } catch (e: Exception) {
                    callback.invoke(false)
                }
            }
            job.await()
            callback.invoke(true)
        }
    }

    override fun addNote(note: Note, callback: SuccessCallbak) {
        performOperationWithTimeout({ databaseClient.noteDAO().addNote(note) },callback)
    }

    override fun updateNote(note: Note, callback: SuccessCallbak) {
        performOperationWithTimeout({ databaseClient.noteDAO().updateNote(note) }, callback)

    }

    override fun deleteNote(note: Note, callback: SuccessCallbak) {
        performOperationWithTimeout({ databaseClient.noteDAO().deleteNote(note) }, callback)
    }

    override fun retrieveNotes(callback: (List<Note>?) -> Unit) {
        GlobalScope.launch {

            val job = async {
                withTimeoutOrNull(TIMEOUT_DURATION_MILIS) {
                    databaseClient.noteDAO().retrieveNotes()
                }
            }
            callback.invoke(job.await())
        }
    }

}