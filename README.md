# Android practic

Каркас Android-проєкту для практичних робіт з мобільної розробки на Kotlin і Jetpack Compose.

Гілка `main` містить лише заготовку: шаблон **Empty Activity** з налаштованою Gradle-збіркою і прикладами тестів. Кожна практична робота виконується в окремій гілці:

| Гілка | Робота |
|---|---|
| `lab-01-first-app` | Основи Kotlin (KotlinBasics) і перший Compose-застосунок з лічильником (HelloMobile) |

## Структура

```
HelloMobile/
├── settings.gradle.kts          # назва проєкту і список модулів
├── build.gradle.kts             # кореневий файл збірки (плагіни)
├── gradle/libs.versions.toml    # версії бібліотек і плагінів
├── gradlew, gradlew.bat         # Gradle Wrapper
└── app/
    ├── build.gradle.kts         # налаштування застосунку і залежності
    └── src/
        ├── main/                # код застосунку, ресурси, AndroidManifest.xml
        ├── test/                # unit-тести (запускаються на комп'ютері)
        └── androidTest/         # інструментальні тести (на емуляторі або пристрої)
```

## Як запустити

**Android Studio:** **File → Open**, вибрати папку `HelloMobile`, дочекатися синхронізації Gradle і натиснути **▶ Run**.

**Командний рядок (Windows), з папки `HelloMobile`:**

```bat
gradlew.bat assembleDebug          :: зібрати APK
gradlew.bat installDebug           :: встановити на емулятор або пристрій
```

## Тести

```bat
gradlew.bat test                   :: unit-тести (app/src/test)
gradlew.bat connectedAndroidTest   :: інструментальні тести, потрібен запущений емулятор або пристрій
```

В Android Studio тест можна запустити кнопкою **▶** біля класу або методу тесту.

## Версії

| Що | Версія |
|---|---|
| Kotlin | 2.2.10 |
| Android Gradle Plugin | 9.4.1 |
| Gradle | 9.6.0 |
| Jetpack Compose BOM | 2025.08.00 |
| compileSdk / targetSdk | 37 |
| minSdk | 24 |
