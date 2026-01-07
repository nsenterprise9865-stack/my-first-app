package com.example.myfirstapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.myfirstapp.model.Note
import com.example.myfirstapp.repository.NoteRepository

class NoteViewModel : ViewModel() {
    private val _notes = mutableStateListOf<Note>()
    val notes: SnapshotStateList<Note> = _notes

    init {
        // Load initial data (empty for now)
        refreshNotes()
    }

    private fun refreshNotes() {
        _notes.clear()
        _notes.addAll(NoteRepository.getAll())
    }

    fun addNote(title: String, content: String) {
        val note = Note(title = title, content = content)
        NoteRepository.insert(note)
        _notes.add(note)
    }

    fun updateNote(id: Long, title: String, content: String) {
        val existing = NoteRepository.getById(id) ?: return
        val updated = existing.copy(title = title, content = content)
        NoteRepository.update(updated)
        val index = _notes.indexOfFirst { it.id == id }
        if (index >= 0) {
            _notes[index] = updated
        }
    }

    fun deleteNote(id: Long) {
        NoteRepository.delete(id)
        _notes.removeAll { it.id == id }
    }

    fun getNoteById(id: Long): Note? = NoteRepository.getById(id)
}
