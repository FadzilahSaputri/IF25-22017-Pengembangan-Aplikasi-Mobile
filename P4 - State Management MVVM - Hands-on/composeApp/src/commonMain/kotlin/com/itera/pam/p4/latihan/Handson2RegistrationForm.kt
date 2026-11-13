package com.itera.pam.p4.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Latihan 2: Form dengan State Hoisting (slide P4 hal. 30)
// Tugas: Buat reusable TextField component dengan state hoisting —
// komponennya sendiri STATELESS (tidak punya state internal), state-nya
// dipegang oleh parent (RegistrationForm) dan dikirim turun lewat parameter.
//
// Checklist:
// [x] Stateless LabeledTextField
// [x] Parameter: value, onValueChange
// [x] State hoisted ke parent
// [x] Name dan Email field
// [x] Preview yang menampilkan "Hello, [name]!"
// [x] Gunakan 1 komponen untuk kedua field

// Stateless TextField component
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    // TODO: Implement dengan OutlinedTextField
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp) // Memberikan jarak bawah agar tidak menempel dengan elemen berikutnya
    )
}

// Parent yang menyimpan state
@Composable
fun Handson2Screen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Latihan 2: Form dengan State Hoisting",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LabeledTextField(
            label = "Name",
            value = name,
            onValueChange = { name = it }
        )
        
        // TODO: Tambahkan email field pakai LabeledTextField yang sama, hoisted ke `email`
        LabeledTextField(
            label = "Email",
            value = email,
            onValueChange = { email = it }
        )
        
        // TODO: Tampilkan preview data, misal: Text("Hello, $name! Email: $email")
        Text(
            text = "Hello, $name!\nEmail: $email",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
