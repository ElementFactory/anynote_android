package com.luckhost.lockscreen_notes.presentation

import androidx.lifecycle.ViewModel
import com.luckhost.domain.models.NoteModel
import com.luckhost.domain.useCases.keys.DeleteHashUseCase
import com.luckhost.domain.useCases.keys.GetHashesUseCase
import com.luckhost.domain.useCases.keys.SaveHashesUseCase
import com.luckhost.domain.useCases.objects.DeleteNoteUseCase
import com.luckhost.domain.useCases.objects.GetNotesUseCase
import com.luckhost.domain.useCases.objects.SaveNoteUseCase
import java.util.Date

class MainViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getHashesUseCase: GetHashesUseCase,
    private val saveHashesUseCase: SaveHashesUseCase,
    private val deleteHashUseCase: DeleteHashUseCase,
): ViewModel() {
    private var hashesList: MutableList<Int> = mutableListOf()
    private var notesList: MutableList<NoteModel> = mutableListOf()

    init {
        hashesList = getHashesUseCase.execute().toMutableList()
        notesList = getNotesUseCase.execute(hashesList).toMutableList()
    }

    fun createNote(
        header: String,
        content: String,
        deadLine: Date,
        coordinateX: Int,
        coordinateY: Int,
    ) {
        val modelToSave = NoteModel(
            header = header,
            content= content,
            deadLine = deadLine,
            coordinateX = coordinateX,
            coordinateY = coordinateY
        )
        saveNoteUseCase.execute(modelToSave)

        hashesList.add(modelToSave.hashCode())
        saveHashesUseCase.execute(hashesList.toList())
    }

    fun getNotes(): List<NoteModel> {
        return notesList
    }
}