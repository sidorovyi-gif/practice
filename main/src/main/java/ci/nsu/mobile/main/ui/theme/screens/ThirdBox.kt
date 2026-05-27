package ci.nsu.mobile.main.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ThirdBox() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Green // Укажите нужный цвет
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("SecondActivity")
        }
    }
}