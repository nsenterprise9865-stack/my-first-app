package com.example.myfirstapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfirstapp.viewmodel.NoteViewModel

@Composable
fun NoteEditScreen(
    noteId: Long,
    viewModel: NoteViewModel,
    onNavigateBack: () -> Unit
) {
    val isNew = noteId == -1L
    val existingNote = if (isNew) null else viewModel.getNoteById(noteId)

    val titleState = remember { mutableStateOf(existingNote?.title ?: "") }
    val contentState = remember { mutableStateOf(existingNote?.content ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(if (isNew) "New Note" else "Edit Note") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = titleState.value,
                onValueChange = { titleState.value = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = contentState.value,
                onValueChange = { contentState.value = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                maxLines = 10
            )
            Button(
                onClick = {
                    if (isNew) {
                        viewModel.addNote(titleState.value, contentState.value)
                    } else {
                        viewModel.updateNote(noteId, titleState.value, contentState.value)
                    }
                    onNavigateBack()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Save")
            }
        }
    }
}
