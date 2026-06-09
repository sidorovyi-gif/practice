package ci.nsu.mobile.main.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable


fun CountsSecond(termMonths: Int,
                 selectedRate: String,
                 onRateSelected: (String) -> Unit,
                 onBackToTermInput: () -> Unit // Возврат к вводу срока
) {
    var expanded by remember { mutableStateOf(false) }

    // Логика доступных ставок в зависимости от срока
    val availableRates = when {
        termMonths < 6 -> listOf("15%")
        termMonths in 6..11 -> listOf("10%")
        termMonths >= 12 -> listOf("5%")
        else -> emptyList() // Если срок <= 0
    }

    Column(modifier = Modifier.padding(16.dp)) {

        // ─── Случай 1: Срок не указан или некорректен ───
        if (termMonths <= 0) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "⚠️ Срок не указан или некорректен",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Для выбора процентной ставки необходимо указать корректный срок вклада (больше 0 месяцев)",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onBackToTermInput,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text("Ввести корректный срок")
                    }
                }
            }
        }
        // ─── Случай 2: Срок указан корректно ───
        else {
            // Показываем информацию о сроке
            Text(
                text = "Срок вклада: $termMonths мес.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Выпадающий список ставок
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = selectedRate.ifEmpty { "Выберите ставку" },
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Процентная ставка") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    isError = selectedRate.isEmpty() // Подсветка если ставка не выбрана
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    availableRates.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                onRateSelected(option)
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Подсказка под полем
            if (selectedRate.isEmpty()) {
                Text(
                    text = "Доступные ставки зависят от срока вклада",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}