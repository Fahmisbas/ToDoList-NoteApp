package com.fahmisbas.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fahmisbas.notes.models.Note
import com.fahmisbas.notes.models.Tag
import com.fahmisbas.notes.models.TaskEntity
import com.fahmisbas.notes.models.ToDo

const val DATABASE_SCHEMA_VERSION = 1;
const val DB_NAME = "local-db"

@Database(version = DATABASE_SCHEMA_VERSION, entities = [TaskEntity::class, ToDo::class,Tag::class, Note::class])
abstract class RoomDatabaseClient : RoomDatabase() {

    abstract fun noteDAO() : NoteDAO
    abstract fun taskDao() : TaskDAO

    companion object{
        private var instance : RoomDatabaseClient? = null

        fun getInstance(context: Context) : RoomDatabaseClient {
            if(instance == null) {
                instance = createDatabase(context)
            }
            return instance!!
        }

        private fun createDatabase(context : Context) : RoomDatabaseClient {
            return Room.databaseBuilder(context,RoomDatabaseClient::class.java, DB_NAME).build()
        }
    }
}