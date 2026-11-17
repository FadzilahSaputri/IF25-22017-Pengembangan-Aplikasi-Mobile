// Hands-on 2: Interface & Data Class
// Tugas: Buat interface Payable dengan fungsi calculateSalary(), lalu implementasikan
// lewat data class Employee. Manfaatkan fitur bawaan data class: toString(), equals(),
// dan copy().

interface Payable {
    // TODO 1: Deklarasikan fungsi abstrak calculateSalary(): Double di interface ini.
    fun calculateSalary(): Double
}

// TODO 2: Jadikan class ini "data class" dan implement interface Payable.
data class Employee(
    val name: String,
    val baseSalary: Double,
    val bonus: Double
) : Payable {

    // TODO 3: Override calculateSalary() agar mengembalikan baseSalary + bonus.
    override fun calculateSalary(): Double {
        return baseSalary + bonus
    }
}

fun main() {
    val alice = Employee("Alice", baseSalary = 5_000_000.0, bonus = 500_000.0)

    // TODO 4: Gunakan alice.copy(...) untuk membuat "bob" dengan name = "Bob"
    val bob = alice.copy(name = "Bob")

    println("Gaji ${alice.name}: ${alice.calculateSalary()}")
    println("Gaji ${bob.name}: ${bob.calculateSalary()}")

    // TODO 5: Buat "aliceDuplicate" dengan instance baru yang datanya identik dengan alice
    val aliceDuplicate = Employee("Alice", baseSalary = 5_000_000.0, bonus = 500_000.0)
    println("alice == aliceDuplicate? ${alice == aliceDuplicate}")

    // toString() bawaan data class akan mencetak semua property secara otomatis.
    println(alice)
}
