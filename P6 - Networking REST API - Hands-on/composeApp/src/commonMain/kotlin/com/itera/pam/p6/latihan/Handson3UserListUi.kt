package com.itera.pam.p6.latihan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

// Hands-on 3: Tampilkan Data di Compose dengan Loading/Error UI
// Tugas: pakai UserListStateHolder (dari Handson2Repository.kt) untuk
// menampilkan:
// - CircularProgressIndicator() selagi UiState.Loading
// - LazyColumn berisi nama & email user selagi UiState.Success
// - Pesan error + tombol "Coba Lagi" (yang memanggil ulang load()) selagi
//   UiState.Error

@Composable
fun Handson3Screen() {
    // TODO 1: buat state holder
    val holder = remember { UserListStateHolder() }
    
    // TODO 2: collect state-nya
    val state by holder.state.collectAsState()
    
    // TODO 4: buat scope coroutine untuk tombol retry
    val scope = rememberCoroutineScope()

    // TODO 3: panggil holder.load() sekali saat pertama kali screen ini muncul
    LaunchedEffect(Unit) {
        holder.load()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Daftar User (Ktor API)",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // TODO 5: buat `when (state)` yang menangani 3 kasus
        when (val currentState = state) {
            is UiState.Loading -> {
                // Tampilan Loading (Lingkaran Berputar di tengah layar)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            
            is UiState.Success -> {
                // Tampilan Sukses (List Data User)
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(currentState.users) { user ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = user.name, fontWeight = FontWeight.Bold)
                                Text(text = user.email, color = Color.Gray)
                            }
                        }
                    }
                }
            }
            
            is UiState.Error -> {
                // Tampilan Error (Pesan Error + Tombol Coba Lagi di tengah layar)
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Error: ${currentState.message}",
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Button(onClick = {
                        // Memanggil ulang fungsi load() di dalam coroutine scope
                        scope.launch { holder.load() }
                    }) {
                        Text("Coba Lagi")
                    }
                }
            }
        }
    }
}
