package basics

// Запис показника здоров'я (як у підрозділі 1.4 посібника)
data class HealthRecord(val id: Int, val title: String, val value: Double, val unit: String)

// Тестовий набір записів за один день
val dayRecords = listOf(
    HealthRecord(1, "Вода", 0.25, "л"),
    HealthRecord(2, "Пульс", 72.0, "уд/хв"),
    HealthRecord(3, "Вода", 0.5, "л"),
    HealthRecord(4, "Сон", 5.5, "год"),
    HealthRecord(5, "Пульс", 85.0, "уд/хв"),
    HealthRecord(6, "Вода", 0.3, "л"),
    HealthRecord(7, "Пульс", 64.0, "уд/хв"),
    HealthRecord(8, "Сон", 7.5, "год"),
)
