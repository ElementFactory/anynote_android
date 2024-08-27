package com.luckhost.domain.useCases.objects

import com.luckhost.domain.repository.NotesRepositoryInterface

class DeleteNoteUseCase(
    private val repository: NotesRepositoryInterface,
) {
    fun execute(noteHash: String) {
        repository.deleteNote(noteHash)
    }
}