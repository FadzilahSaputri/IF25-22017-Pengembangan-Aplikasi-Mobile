package com.itera.pam.p1.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.itera.pam.p1.getPlatformName

// Hands-on 3: Layout Dasar — Profile Card
// Tugas: Susun sebuah "kartu profil" sederhana berisi nama, NIM, dan platform
// yang sedang berjalan, menggunakan Card, Column, Row, dan Modifier.

@Composable
fun Handson3Screen() {
    // TODO 1: Bungkus semua konten dengan Card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // TODO 2: Di dalam Card, buat Column
        Column(modifier = Modifier.padding(16.dp)) {
            // TODO 3: Di dalam Column, tambahkan Text() untuk nama kamu
            Text(
                text = "Nama: Fadzilah Saputri",
                fontWeight = FontWeight.Bold
            )

            // TODO 4: Tambahkan Text() untuk NIM kamu
            Text(text = "NIM: 123140149")

            // TODO 5: Tambahkan Row berisi Text("Platform: ") dan Text(getPlatformName())
            Row {
                Text(text = "Platform: ")
                Text(text = getPlatformName())
            }
        }
    }
}
