package basics

import java.util.Locale

// Самостійна робота: підсумок дня
data class DaySummary(
    val totalWater: Double,      // літрів води за день
    val averagePulse: Double?,   // середній пульс; null, якщо вимірювань не було
    val lastSleep: Double?,      // тривалість останнього сну; null, якщо записів немає
)

// Функція-розширення для списку записів
fun List<HealthRecord>.summary(): DaySummary {
    val pulses = filter { it.title == "Пульс" }.map { it.value }
    return DaySummary(
        totalWater = filter { it.title == "Вода" }.sumOf { it.value },
        averagePulse = if (pulses.isEmpty()) null else pulses.average(),
        lastSleep = lastOrNull { it.title == "Сон" }?.value,
    )
}

// Функція-розширення для форматування чисел
fun Double.format(digits: Int = 1): String = String.format(Locale.US, "%.${digits}f", this)

fun daySummaryDemo() {
    val s = dayRecords.summary()
    println(s)
    println("Вода за день: ${s.totalWater.format(2)} л")
    println("Середній пульс: ${s.averagePulse?.format() ?: "немає даних"} уд/хв")
    println("Останній сон: ${s.lastSleep?.let { "${it.format()} год (${sleepQuality(it)})" } ?: "немає даних"}")

    // Перевірка порожнього списку: nullable-поля і оператор ?:
    val empty = emptyList<HealthRecord>().summary()
    println("Порожній день: $empty")
    println("Середній пульс: ${empty.averagePulse?.format() ?: "немає даних"}")
}
