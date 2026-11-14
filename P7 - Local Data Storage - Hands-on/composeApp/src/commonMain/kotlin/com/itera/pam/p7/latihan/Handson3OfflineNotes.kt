package com.itera.pam.p7.latihan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.itera.pam.p7.db.AppDatabase

// Hands-on 3: Offline-First — Tampilkan & Hapus Catatan
// Gunakan CatatanRepository dari Handson2Repository.kt (yang sudah kamu
// lengkapi) untuk menampilkan daftar catatan dalam LazyColumn, form untuk
// menambah catatan baru, dan tombol hapus per item.

@Composable
fun Handson3Screen(database: AppDatabase) {
    // TODO 1: buat repository
    val repository = remember { CatatanRepository(database) }
    
    // TODO 2: collect repository.getAll() sebagai Compose state
    val catatanList by repository.getAll().collectAsState(initial = emptyList())

    // State untuk form input penambahan catatan
    var judul by remember { mutableStateOf("") }
    var isi by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Hands-on 3: Offline-First Notes",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // TODO 4: 2 OutlinedTextField (judul, isi) + Button("Tambah")
        OutlinedTextField(
            value = judul,
            onValueChange = { judul = it },
            label = { Text("Judul Catatan") },
            modifier = Modifier.fillMaxWidth()
