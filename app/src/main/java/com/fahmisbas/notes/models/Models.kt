package com.fahmisbas.notes.models

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.util.*


class Task @JvmOverloads constructor(
    title: String,
    @Relation(
        parentColumn = "uid",
        entityColumn = "taskId",
        entity = ToDo::class
    )
    val toDos: MutableList<ToDo> = mutableListOf(),
    tag: Tag? = null
) : TaskEntity(
    title = title,
    tag = tag
) {
    init {
        toDos.forEach {
            it.taskId = uid
        }
    }
}

@Entity(tableName = "tasks")
open class TaskEntity(
    @PrimaryKey
    val uid : Long = UUID.randomUUID().leastSignificantBits,
    @ColumnInfo
    var title: String,
    @Embedded
    var tag: Tag? = null
)


@Entity(tableName = "todos")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0,
    @ForeignKey(
        parentColumns = ["uid"],
        childColumns = ["taskId"],
        entity = TaskEntity::class,
        onDelete = CASCADE
    )
    var taskId : Long? = null,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var isComplete: Boolean = false
)

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0,
    @ColumnInfo
    var description: String,
    @Embedded
    var tag: Tag? = null
)

@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "colour_resource_id")
    var colourResId: Int
)

