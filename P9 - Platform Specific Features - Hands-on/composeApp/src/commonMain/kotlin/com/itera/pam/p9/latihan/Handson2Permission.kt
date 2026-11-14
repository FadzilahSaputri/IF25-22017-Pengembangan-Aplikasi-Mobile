package com.itera.pam.p9.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.itera.pam.p9.hasCameraPermission // TODO 1: Import fungsi hasCameraPermission

// Hands-on 2: Permission Check (Platform-Aware)
// `hasCameraPermission()` sudah diimplementasikan lewat expect/actual: cek
// ASLI di Android (ContextCompat.checkSelfPermission), stub `true` di
// iOS/Desktop karena model permission-nya berbeda dan di luar cakupan
// hands-on ini.

@Composable
fun Handson2Screen() {
    // Memanggil fungsi expect/actual untuk mengecek status
    val isGranted = hasCameraPermission()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hands-on 2: Permission Check",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // TODO 2: Tampilkan status izin kamera
        Text(text = "Status izin kamera: $isGranted") 
        
        // TODO 3: Tampilkan pesan berbeda tergantung true/false-nya
        if (isGranted) {
            Text(
                text = "Akses Diberikan: Kamu bisa menggunakan fitur kamera sekarang.",
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )
        } else {
            Text(
                text = "Akses Ditolak: Aplikasi membutuhkan izin kamera untuk fitur ini.",
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )
        }

        Button(onClick = { /* simulasi tombol minta izin, di luar cakupan hands-on ini */ }) {
            Text("Simulasikan Minta Izin")
        }
    }
}
