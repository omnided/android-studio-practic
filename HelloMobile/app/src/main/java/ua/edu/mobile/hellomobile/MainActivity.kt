package ua.edu.mobile.hellomobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.mobile.hellomobile.ui.theme.HelloMobileTheme

// Кількість натискань, після якої змінюється повідомлення
const val CLICK_GOAL = 10

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloMobileTheme {
                Scaffold(Modifier.fillMaxSize()) { padding ->
                    Greeting("студенте", Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // rememberSaveable зберігає лічильник і після повороту екрана
    var clicks by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Привіт, $name!", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text("Мій перший застосунок на Jetpack Compose",
            style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(24.dp))

        Row {
            Button(onClick = { clicks++ }) { Text("Натисни мене") }
            Spacer(Modifier.width(12.dp))
            // Кнопка «Скинути» обнуляє лічильник; неактивна, коли скидати нічого
            OutlinedButton(onClick = { clicks = 0 }, enabled = clicks > 0) {
                Text("Скинути")
            }
        }
        Spacer(Modifier.height(16.dp))
        Text("Кількість натискань: $clicks", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        // Текст залежить від стану: Compose сам оновить його при зміні clicks
        Text(
            text = progressMessage(clicks),
            style = MaterialTheme.typography.bodyLarge,
            color = if (clicks >= CLICK_GOAL) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

// Повідомлення залежно від кількості натискань (when як вираз)
fun progressMessage(clicks: Int): String = when {
    clicks == 0 -> "Натисніть кнопку, щоб почати"
    clicks < CLICK_GOAL -> "До мети залишилось ${CLICK_GOAL - clicks}"
    clicks == CLICK_GOAL -> "Вітаємо! Ви досягли $CLICK_GOAL натискань!"
    else -> "Мету досягнуто, продовжуйте!"
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloMobileTheme { Greeting("студенте") }
}
