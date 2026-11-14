package com.itera.pam.p6.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Hands-on 2: Repository Pattern & Loading State
// Bungkus fetchUsers() (dari Handson1KtorClient.kt) di balik sebuah Repository,
// lalu buat state holder sederhana yang merepresentasikan status pengambilan
// data: Loading / Success / Error.
//
// Catatan desain: state holder ini sengaja berupa class biasa (bukan
// androidx.lifecycle.ViewModel) supaya hands-on ini tetap fokus ke konsep
// networking/repository — pola ViewModel yang lebih lengkap akan dipelajari
// di Pertemuan 4 (State Management MVVM).

class UserRepository {
    suspend fun getUsers(): List<RemoteUser> = fetchUsers()
}

sealed class UiState {
    data object Loading : UiState()
    data class Success(val users: List<RemoteUser>) : UiState()
    data class Error(val message: String) : UiState()
}

class UserListStateHolder(
    private val repository: UserRepository = UserRepository()
) {
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state.asStateFlow()

    suspend fun load() {
        // TODO 1: set _state.value ke UiState.Loading sebelum mulai fetch
        _state.value = UiState.Loading
        
        try {
            // TODO 2: panggil repository.getUsers() di dalam try-catch
            val users = repository.getUsers()
            
            // TODO 3: jika berhasil, set _state.value ke UiState.Success(users)
            _state.value = UiState.Success(users)
        } catch (e: Exception) {
            // TODO 4: jika gagal (catch Exception e), set _state.value ke UiState.Error
            // Kita gunakan elvis operator (?:) untuk memberi pesan default jika e.message kosong
            _state.value = UiState.Error(e.message ?: "Terjadi kesalahan pada jaringan")
        }
    }
}

@Composable
fun Handson2Screen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hands-on 2: Repository Pattern & Loading State",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text("State Holder dan Repository sudah diimplementasikan dengan blok try-catch untuk menangani kondisi Loading, Success, dan Error.")
    }
}
