package com.fahmisbas.notes.foundations

import com.fahmisbas.notes.notes.INoteModel
import com.fahmisbas.notes.notes.NoteLocalModel
import com.fahmisbas.notes.tasks.ITaskModel
import com.fahmisbas.notes.tasks.TaskLocalModel
import toothpick.Toothpick
import toothpick.config.Module

object ApplicationScope {
    val scope = Toothpick.openScope(this).apply {
        installModules(ApplicationModule)
    }
}

object ApplicationModule : Module() {
    init {
        bind(INoteModel::class.java).toInstance(NoteLocalModel())
        bind(ITaskModel::class.java).toInstance(TaskLocalModel())
    }
}