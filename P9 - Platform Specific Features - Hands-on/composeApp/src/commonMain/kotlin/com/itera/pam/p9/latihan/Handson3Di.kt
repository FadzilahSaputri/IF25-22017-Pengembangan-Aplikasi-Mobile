package com.itera.pam.p9.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.itera.pam.p9.di.GreetingRepository // TODO 2: Import GreetingRepository
import org.koin.compose.koinInject // TODO 1: Import koinInject

// Hands-on 3: Dependency Injection dengan Koin
// Modul Koin (`appModule`) dan `initKoin()` sudah disiapkan di
// com.itera.pam.p9.di.AppModule, dan sudah dipanggil dari MainActivity /
// MainViewController / main.kt sebelum UI dijalankan.

@Composable
fun Handson3Screen() {
    // TODO 3: Ambil instance GreetingRepository dengan koinInject
    // Koin akan otomatis mencarikan instance GreetingRepository yang sudah dibuat di appModule
    val repository = koinInject<GreetingRepository>()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hands-on 3: Dependency Injection dengan Koin",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // TODO 4: Tampilkan hasil repository.greeting() di dalam Text()
        Text(text = repository.greeting())
    }
}
