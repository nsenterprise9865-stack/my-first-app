package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfirstapp.ui.theme.AppTheme
import com.example.myfirstapp.ui.NoteEditScreen
import com.example.myfirstapp.ui.NoteListScreen
import com.example.myfirstapp.viewmodel.NoteViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.background
                ) {
                    AppNavigator()
                }
            }
        }
    }
}

@Composable
fun AppNavigator(noteViewModel: NoteViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            NoteListScreen(
                viewModel = noteViewModel,
                onAddNote = { navController.navigate("edit/-1") },
                onEditNote = { noteId -> navController.navigate("edit/$noteId") }
            )
        }
        composable("edit/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toLongOrNull() ?: -1L
            NoteEditScreen(
                noteId = noteId,
                viewModel = noteViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
