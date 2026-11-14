package com.itera.pam.p6.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// Hands-on 1: Ktor Client Dasar & GET Request
// Endpoint yang dipakai di seluruh hands-on Pertemuan 6:
// https://jsonplaceholder.typicode.com/users (fake REST API gratis untuk belajar,
// mengembalikan array JSON berisi data user).

@Serializable
data class RemoteUser(
    val id: Int,
    val name: String,
    val email: String
)

// TODO 1: Buat HttpClient dengan ContentNegotiation + json() supaya Ktor bisa
//         otomatis decode response JSON menjadi objek Kotlin.
val client: HttpClient = HttpClient {
    install(ContentNegotiation) {
        // ignoreUnknownKeys = true berguna agar aplikasi tidak crash kalau
        // API tiba-tiba mengirim data tambahan yang tidak ada di data class kita
        json(Json { ignoreUnknownKeys = true })
    }
}

// TODO 2: Lakukan GET request ke "https://jsonplaceholder.typicode.com/users"
//         dan decode response body-nya menjadi List<RemoteUser>.
suspend fun fetchUsers(): List<RemoteUser> {
    // Memanggil endpoint dan otomatis mengubah format JSON menjadi List<RemoteUser>
    return client.get("https://jsonplaceholder.typicode.com/users").body()
}

@Composable
fun Handson1Screen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hands-on 1: Ktor Client Dasar & GET Request",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text("Lengkapi TODO di Handson1KtorClient.kt, lalu lihat Solusi 1 untuk contoh pemanggilan fetchUsers() dari Composable.")
    }
}
