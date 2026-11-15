package com.itera.pam.p11.latihan

import com.itera.pam.p11.Counter
import kotlin.test.Test
import kotlin.test.assertEquals

// Hands-on 1: Unit Test Dasar — Counter (Arrange-Act-Assert)
// Tugas: lengkapi 3 test di bawah mengikuti pola AAA.
// Jalankan lewat IDE (klik ikon ▶️ di sebelah class/fun) atau
// `./gradlew :composeApp:desktopTest`.
class CounterTest {

    @Test
    fun incrementMenambahNilaiCounter() {
        // Arrange
        val counter = Counter()

        // Act
        // TODO 1: panggil counter.increment() dua kali
        counter.increment()
        counter.increment()

        // Assert
        // TODO 2: assertEquals(2, counter.value.value)
        assertEquals(2, counter.value.value)
    }

    @Test
    fun decrementTidakBolehMembuatNilaiDiBawahNol() {
        // Arrange
        val counter = Counter()

        // Act
        // TODO 3: panggil counter.decrement() padahal nilai masih 0
        counter.decrement()

        // Assert
        // TODO 4: pastikan nilainya tetap 0, bukan -1
        assertEquals(0, counter.value.value)
    }

    @Test
    fun resetMengembalikanNilaiKeNol() {
        // Arrange
        val counter = Counter()
        counter.increment()
        counter.increment()
        counter.increment()

        // Act
        // TODO 5: panggil counter.reset()
        counter.reset()

        // Assert
        // TODO 6: assertEquals(0, counter.value.value)
        assertEquals(0, counter.value.value)
    }
}
