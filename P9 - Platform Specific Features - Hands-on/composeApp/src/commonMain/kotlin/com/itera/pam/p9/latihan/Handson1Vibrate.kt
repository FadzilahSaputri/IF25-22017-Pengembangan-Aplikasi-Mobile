package com.itera.pam.p9.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.itera.pam.p9.vibrate // TODO 1: Import fungsi vibrate

// Hands-on 1: expect/actual Lanjutan — Getar Perangkat
// `vibrate()` sudah diimplementasikan lewat expect/actual (lihat Platform.kt
// dan actual-nya di androidMain/iosMain/desktopMain) — tugasmu di sini hanya
// memanggilnya dari tombol di bawah.

@Composable
fun Handson1Screen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hands-on 1: Getar Perangkat (expect/actual)",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Button(
            onClick = { vibrate() } // TODO 2: Panggil vibrate() di dalam onClick
        ) {
            Text("Getar!")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // TODO 3: Text penjelasan perbedaan respon tiap platform
        Text(
            text = "Catatan: Jika dijalankan di HP (Android & iOS), perangkat akan bergetar secara fisik. Namun, jika dijalankan di komputer (Desktop), perangkat tidak bisa bergetar dan hanya akan mencetak log ke console."
        )
    }
}
