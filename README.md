# Android practic

Практичні роботи з мобільної розробки на Kotlin. Репозиторій складається з двох незалежних проєктів:

| Проєкт | Що це | Збірка |
|---|---|---|
| [`KotlinBasics`](#kotlinbasics) | Консольна програма: основи мови Kotlin на прикладі показників здоров'я | IntelliJ IDEA / Android Studio, без Gradle |
| [`HelloMobile`](#hellomobile) | Android-застосунок на Jetpack Compose: привітання і лічильник натискань | Gradle |

Кожен проєкт відкривається окремо: у **File → Open** треба вибирати саме папку `KotlinBasics` або `HelloMobile`, а не корінь `android practic`.

---

## KotlinBasics

Консольна програма, яка обробляє записи показників здоров'я (вода, пульс, сон) і виводить результати в консоль.

### Що зроблено

- **`HealthRecord`** (`HealthRecord.kt`): `data class` запису показника (`id`, `title`, `value`, `unit`) і тестовий набір записів за один день `dayRecords`.
- **Основне завдання: `sleepQuality()`** (`SleepQuality.kt`). Функція оцінює якість сну за допомогою виразу `when`:
  - менше 6 год — «недостатньо»;
  - від 6 до 9 год включно — «норма»;
  - більше 9 год — «забагато».

  Демо проходить по записах сну за тиждень (`for` з `withIndex()`) і рахує, скільки ночей потрапило в кожну категорію (`groupingBy { }.eachCount()`).
- **Самостійна робота: `DaySummary`** (`DaySummary.kt`). Підсумок дня:
  - загальна кількість води (`filter` + `sumOf`);
  - середній пульс, `null`, якщо вимірювань не було (`average()`);
  - тривалість останнього сну, `null`, якщо записів немає (`lastOrNull`);
  - функції-розширення `List<HealthRecord>.summary()` і `Double.format()`;
  - робота з nullable-значеннями через `?.`, `?.let` і `?:`, зокрема перевірка порожнього списку.
- **`Main.kt`**: точка входу `main()`, запускає обидва демо по черзі.

### Структура

```
KotlinBasics/
├── KotlinBasics.iml             # налаштування модуля для IDE (підключає Kotlin stdlib)
├── run_output.txt               # збережений вивід програми
└── src/main/kotlin/basics/
    ├── Main.kt                  # main()
    ├── HealthRecord.kt          # data class і тестові дані
    ├── SleepQuality.kt          # sleepQuality() + демо
    └── DaySummary.kt            # DaySummary, функції-розширення + демо
```

### Як запустити

**Через IntelliJ IDEA або Android Studio:**

1. **File → Open** і вибрати папку `KotlinBasics`.
2. Відкрити `src/main/kotlin/basics/Main.kt`.
3. Натиснути зелену кнопку **▶** біля `fun main()` і вибрати **Run 'MainKt'**.

Результат з'явиться у вікні **Run** внизу.

**З командного рядка** (якщо окремо встановлено компілятор [`kotlinc`](https://kotlinlang.org/docs/command-line.html)):

```bat
kotlinc src\main\kotlin -include-runtime -d KotlinBasics.jar
java -jar KotlinBasics.jar
```

### Очікуваний результат

```
===== Завдання: sleepQuality() =====
День 1: 5.0 год – недостатньо
День 2: 6.0 год – норма
День 3: 7.5 год – норма
День 4: 9.0 год – норма
День 5: 9.5 год – забагато
День 6: 4.5 год – недостатньо
День 7: 8.0 год – норма
Підсумок за тиждень: {недостатньо=2, норма=4, забагато=1}

===== Самостійна робота: DaySummary =====
DaySummary(totalWater=1.05, averagePulse=73.66666666666667, lastSleep=7.5)
Вода за день: 1.05 л
Середній пульс: 73.7 уд/хв
Останній сон: 7.5 год (норма)
Порожній день: DaySummary(totalWater=0.0, averagePulse=null, lastSleep=null)
Середній пульс: немає даних
```

---

## HelloMobile

Перший Android-застосунок на Kotlin і Jetpack Compose. Він показує привітання і рахує натискання кнопки.

### Що зроблено

- **Привітальний екран.** Заголовок «Привіт, студенте!» і підзаголовок.
- **Лічильник натискань.** Кнопка «Натисни мене» збільшує лічильник. Значення зберігається через `rememberSaveable`, тому не скидається після повороту екрана.
- **Кнопка «Скинути».** Обнуляє лічильник. Вона неактивна, поки лічильник дорівнює 0.
- **Повідомлення про прогрес.** Текст змінюється залежно від кількості натискань (`when` як вираз):
  - 0 — «Натисніть кнопку, щоб почати»;
  - менше 10 — скільки залишилось до мети;
  - рівно 10 — привітання з досягненням мети (текст стає кольором `primary`);
  - більше 10 — «Мету досягнуто, продовжуйте!».
- **Власна кольорова тема.** Бірюзово-зелена палітра замість стандартної фіолетової. Є світлий і темний варіанти, які перемикаються разом із системною темою телефона.
- **Власна назва застосунку.** «Привіт, Mobile!» (`res/values/strings.xml`).

### Структура

```
HelloMobile/
├── settings.gradle.kts          # назва проєкту і список модулів
├── build.gradle.kts             # кореневий файл збірки (плагіни)
├── gradle.properties            # налаштування Gradle
├── gradle/
│   ├── libs.versions.toml       # версії всіх бібліотек і плагінів
│   └── wrapper/                 # Gradle Wrapper (потрібна версія Gradle)
├── gradlew, gradlew.bat         # скрипти запуску Gradle
└── app/
    ├── build.gradle.kts         # налаштування застосунку і залежності
    └── src/main/
        ├── AndroidManifest.xml  # «паспорт» застосунку: назва, тема, стартова Activity
        ├── res/values/
        │   ├── strings.xml      # рядки (назва застосунку)
        │   └── themes.xml       # XML-тема вікна (до запуску Compose)
        └── java/ua/edu/mobile/hellomobile/
            ├── MainActivity.kt  # екран, лічильник, логіка повідомлень
            └── ui/theme/
                ├── Color.kt     # палітра кольорів
                ├── Type.kt      # стилі тексту (Typography)
                └── Theme.kt     # HelloMobileTheme: збирає кольори і шрифти в тему
```

### Вимоги

- Android Studio (найпростіший спосіб) **або** JDK 17+ і Android SDK
- Android SDK Platform 37
- Емулятор Android або фізичний пристрій з Android 7.0 (API 24) і новіше

### Як запустити

**Через Android Studio:**

1. **File → Open** і вибрати папку `HelloMobile`.
2. Дочекатися синхронізації Gradle (або натиснути **Sync Project with Gradle Files**).
3. Вибрати емулятор або підключений телефон у списку пристроїв.
4. Натиснути **▶ Run** (Shift+F10).

Якщо Android Studio не розпізнає проєкт як Android-проєкт, закрийте його, видаліть `HelloMobile.iml` і папку `.idea`, а потім відкрийте знову. Студія створить їх заново за Gradle-файлами.

**З командного рядка (Windows), з папки `HelloMobile`:**

```bat
gradlew.bat assembleDebug
```

Готовий файл: `app\build\outputs\apk\debug\app-debug.apk`.

Встановити на запущений емулятор або підключений телефон:

```bat
gradlew.bat installDebug
```

Після цього застосунок «Привіт, Mobile!» з'явиться у списку застосунків.

> Файл `local.properties` містить шлях до Android SDK на конкретному комп'ютері. На іншому ПК Android Studio створить його автоматично, або його можна відредагувати вручну (`sdk.dir=...`).

### Версії

| Що | Версія |
|---|---|
| Kotlin | 2.2.10 |
| Android Gradle Plugin | 9.4.1 |
| Gradle | 9.6.0 |
| Jetpack Compose BOM | 2025.08.00 |
| compileSdk / targetSdk | 37 |
| minSdk | 24 |
