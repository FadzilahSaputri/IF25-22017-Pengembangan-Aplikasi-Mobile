package com.itera.pam.p5.latihan

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

// Hands-on 3: Bottom Navigation
// Tugas: buat 3 tab (Home/Search/Profil) dengan NavigationBar yang terhubung
// ke NavHost dan mengatasi masalah penumpukan back stack.

private data class Tab(val route: String, val label: String)

private val tabs = listOf(
    Tab("home", "Home"),
    Tab("search", "Search"),
    Tab("profil", "Profil"),
)

@Composable
fun Handson3Screen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route

                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = currentRoute == tab.route,
                        onClick = {
                            // SOLUSI: Menggunakan opsi navigasi standar untuk Bottom Tabs
                            navController.navigate(tab.route) {
                                // 1. Mencegah penumpukan layar: saat pindah tab, hapus layar 
                                // lain sampai ke Start Destination (biasanya "home")
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // 2. Mencegah layar yang sama dibuka berkali-kali 
                                // saat tombol tab yang sama di-tap berulang kali
                                launchSingleTop = true
                                // 3. Mengembalikan state (misal: posisi scroll) saat 
                                // tab yang sebelumnya pernah dibuka, dibuka kembali
                                restoreState = true
                            }
                        },
                        icon = { /* Kosong sesuai template */ },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { 
                Text("Halaman Home", modifier = Modifier.padding(16.dp)) 
            }
            composable("search") { 
                Text("Halaman Search", modifier = Modifier.padding(16.dp)) 
            }
            composable("profil") { 
                Text("Halaman Profil", modifier = Modifier.padding(16.dp)) 
            }
        }
    }
}
