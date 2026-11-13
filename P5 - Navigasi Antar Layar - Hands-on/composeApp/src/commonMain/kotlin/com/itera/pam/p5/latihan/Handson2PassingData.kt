package com.itera.pam.p5.latihan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// Hands-on 2: Passing Data via Arguments
// Tugas: dari daftar item di Home, kirim `itemId` sebagai argumen route ke
// Detail, lalu tampilkan item yang sesuai.

private data class Item(val id: Int, val nama: String)

private val daftarItem = listOf(
    Item(1, "Buku Kotlin"),
    Item(2, "Laptop"),
    Item(3, "Headphone"),
)

@Composable
fun Handson2Screen() {
    val navController = rememberNavController()

    // TODO 1 & 2 & 3: Menyusun NavHost dan mendaftarkan composable
    NavHost(
        navController = navController, 
        startDestination = "home"
    ) {
        // Rute Home
        composable("home") { 
            HomeListScreen(navController = navController) 
        }
        
        // Rute Detail dengan argumen itemId
        composable(
            route = "detail/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            // Mengambil nilai itemId dari argument (default -1 jika gagal)
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1
            DetailItemScreen(navController = navController, itemId = itemId)
        }
    }
}

@Composable
private fun HomeListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Jarak antar elemen 8.dp
    ) {
        Text(
            text = "Daftar Item",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        daftarItem.forEach { item ->
            Button(
                onClick = {
                    // TODO 4: Navigasi ke detail dengan membawa item.id
                    navController.navigate("detail/${item.id}")
                },
                modifier = Modifier.fillMaxWidth() // Memanjangkan tombol
            ) {
                Text(item.nama)
            }
        }
    }
}

@Composable
private fun DetailItemScreen(navController: NavHostController, itemId: Int) {
    val item = daftarItem.find { it.id == itemId }
    
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Detail Item",
            fontWeight = FontWeight.Bold
        )
        Text("ID: $itemId")
        Text("Nama: ${item?.nama ?: "(tidak ditemukan)"}")
        
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("< Kembali")
        }
    }
}
