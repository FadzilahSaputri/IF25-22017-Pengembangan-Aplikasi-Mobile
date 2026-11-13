package com.itera.pam.p4.latihan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update // Penting untuk fungsi update state

// Latihan 3: Todo App dengan ViewModel — implementasi MVVM pattern (slide P4 hal. 31)
//
// Checklist:
// [x] Data class Todo
// [x] TodoUiState dengan list
// [x] TodoViewModel dengan StateFlow
// [x] Add todo function
// [x] Toggle done function
// [x] TextField untuk input
// [x] LazyColumn untuk list
// [x] Checkbox untuk done

// 1. Data class
data class Todo(val id: Int, val text: String, val done: Boolean)

data class TodoUiState(
    val todos: List<Todo> = emptyList(),
    val input: String = ""
)

// 2. ViewModel
class TodoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    fun onInputChange(text: String) {
        // TODO: update _uiState.input dengan `text`
        _uiState.update { it.copy(input = text) }
    }

    fun addTodo() {
        // TODO: tambahkan Todo baru ke uiState.todos, lalu kosongkan input. 
        // Jangan tambahkan jika input blank.
        _uiState.update { currentState ->
            if (currentState.input.isNotBlank()) {
                val newTodo = Todo(
                    id = currentState.todos.size, // Menggunakan ukuran list sebagai ID
                    text = currentState.input,
                    done = false
                )
                currentState.copy(
                    todos = currentState.todos + newTodo,
                    input = "" // Kosongkan input setelah ditambah
                )
            } else {
                currentState
            }
        }
    }

    fun toggleTodo(id: Int) {
        // TODO: toggle `done` untuk Todo dengan id yang cocok
        _uiState.update { currentState ->
            currentState.copy(
                todos = currentState.todos.map { todo ->
                    if (todo.id == id) todo.copy(done = !todo.done) else todo
                }
            )
        }
    }
}

// 3. Compose UI
@Composable
fun Handson3Screen(viewModel: TodoViewModel = viewModel { TodoViewModel() }) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Latihan 3: Todo App dengan ViewModel",
            fontWeight = FontWeight.Bold
        )

        // TODO: Implement UI
        // Baris untuk Input dan Tombol Tambah
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = uiState.input,
                onValueChange = viewModel::onInputChange,
                label = { Text("Tambah Tugas") },
                modifier = Modifier.weight(1f) // Biar TextField mengisi sisa ruang di kiri tombol
            )
            
            Button(onClick = viewModel::addTodo) {
                Text("Tambah")
            }
        }

        // LazyColumn untuk menampilkan daftar Todo
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(uiState.todos) { todo ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = todo.done,
                        onCheckedChange = { viewModel.toggleTodo(todo.id) }
                    )
                    Text(text = todo.text)
                }
            }
        }
    }
}
