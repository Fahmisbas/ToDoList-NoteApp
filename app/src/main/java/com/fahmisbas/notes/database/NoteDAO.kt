package com.fahmisbas.notes.database

import androidx.room.*
import com.fahmisbas.notes.models.Note

@Dao
interface NoteDAO {

    @Insert
    fun addNote(note : Note)

    @Update
    fun updateNote(note : Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM notes")
    fun retrieveNotes() : MutableList<Note>

}