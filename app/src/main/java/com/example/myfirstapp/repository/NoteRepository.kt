package com.example.myfirstapp.repository

import com.example.myfirstapp.model.Note

object NoteRepository {
    private val notes = mutableListOf<Note>()

    fun getAll(): List<Note> = notes.toList()

    fun getById(id: Long): Note? = notes.find { it.id == id }

    fun insert(note: Note) {
        notes.add(note)
    }

    fun update(updated: Note) {
        notes.replaceAll { if (it.id == updated.id) updated else it }
    }

    fun delete(id: Long) {
        notes.removeAll { it.id == id }
    }
}
