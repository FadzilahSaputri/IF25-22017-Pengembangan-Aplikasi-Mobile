package com.itera.pam.p10.latihan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.itera.pam.p10.MockAiRepository
import kotlinx.coroutines.launch

// Hands-on 3: AI-Powered Chat UI
// Tugas: buat UI chat sederhana: TextField input, tombol "Kirim", daftar
// bubble pesan (isUser: Boolean, text: String) yang bisa di-scroll, dan
// indikator loading saat menunggu jawaban dari AiRepository.ask()
// (pakai MockAiRepository() secara default).

data class ChatMessage(val isUser: Boolean, val text: String)

@Composable
fun Handson3Screen() {
    // TODO 1: Buat state `messages`
    var messages by remember { mutableStateOf(listOf<ChatMessage>()) }

    // TODO 2: Buat state `input` dan `isLoading`
    var input by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val repository = remember { MockAiRepository() }

    // TODO 3: Buat Column utama
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Hands-on 3: AI-Powered Chat UI",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Daftar pesan yang bisa di-scroll
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { message ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
                ) {
                    Card {
                        Text(
                            text = if (message.isUser) "Kamu: ${message.text}" else "AI: ${message.text}",
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }

        // Indikator loading jika isLoading == true
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )
        }

        // Row berisi OutlinedTextField + Button("Kirim")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Tulis pesan...") },
                modifier = Modifier.weight(1f)
            )

            Button(
                enabled = !isLoading && input.isNotBlank(),
                onClick = {
                    // TODO 4: Logika saat tombol "Kirim" diklik
                    val pertanyaan = input
                    messages = messages + ChatMessage(isUser = true, text = pertanyaan)
                    input = ""
                    isLoading = true

                    scope.launch {
                        val hasil = repository.ask(pertanyaan).getOrElse { "Error: ${it.message}" }
                        messages = messages + ChatMessage(isUser = false, text = hasil)
                        isLoading = false
                    }
                }
            ) {
                Text("Kirim")
            }
        }
    }
}
