package com.fahmisbas.notes.notes

import com.fahmisbas.notes.application.NoteApplication
import com.fahmisbas.notes.database.RoomDatabaseClient
import com.fahmisbas.notes.models.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject

const val TIMEOUT_DURATION_MILIS = 3000L

class NoteLocalModel @Inject constructor() : INoteModel {

    private var databaseClient: RoomDatabaseClient =
        RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)

    private suspend fun performOperationWithTimeout(
        function: () -> Unit,
        callback: SuccessCallbak
    ) {
        val job = GlobalScope.async {
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

    override suspend fun addNote(note: Note, callback: SuccessCallbak) {
        performOperationWithTimeout({ databaseClient.noteDAO().addNote(note) }, callback)
    }

    override suspend fun updateNote(note: Note, callback: SuccessCallbak) {
        performOperationWithTimeout({ databaseClient.noteDAO().updateNote(note) }, callback)
    }

    override suspend fun deleteNote(note: Note, callback: SuccessCallbak) {
        performOperationWithTimeout({ databaseClient.noteDAO().deleteNote(note) }, callback)
    }

    override suspend fun retrieveNotes(callback: (List<Note>?) -> Unit) {
        val job = GlobalScope.async {
            withTimeoutOrNull(TIMEOUT_DURATION_MILIS) {
                databaseClient.noteDAO().retrieveNotes()
            }
        }
        callback.invoke(job.await())

    }

}