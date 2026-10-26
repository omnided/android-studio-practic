package basics

// Основне завдання: оцінка якості сну виразом when
fun sleepQuality(hours: Double): String = when {
    hours < 6.0 -> "недостатньо"
    hours <= 9.0 -> "норма"          // 6–9 годин включно
    else -> "забагато"
}

fun sleepQualityDemo() {
    // Записи сну за тиждень
    val sleepRecords = listOf(
        HealthRecord(101, "Сон", 5.0, "год"),
        HealthRecord(102, "Сон", 6.0, "год"),
        HealthRecord(103, "Сон", 7.5, "год"),
        HealthRecord(104, "Сон", 9.0, "год"),
        HealthRecord(105, "Сон", 9.5, "год"),
        HealthRecord(106, "Сон", 4.5, "год"),
        HealthRecord(107, "Сон", 8.0, "год"),
    )

    for ((day, record) in sleepRecords.withIndex()) {
        println("День ${day + 1}: ${record.value} ${record.unit} – ${sleepQuality(record.value)}")
    }

    // Додатково: скільки ночей у кожній категорії
    val stats = sleepRecords.groupingBy { sleepQuality(it.value) }.eachCount()
    println("Підсумок за тиждень: $stats")
}
