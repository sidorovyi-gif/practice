package ci.nsu.mobile.main.ui.theme.screens

import android.R.attr.button
import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class LunchTrayScreen(val title: String) {
    data object FirstBox : LunchTrayScreen("counts")
    data object SecondBox : LunchTrayScreen("history")
    data object ThirdBox : LunchTrayScreen("leave")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // Вместо Scaffold используем обычный Column
    Column(
        modifier = Modifier.fillMaxSize()

    ) {
        Button(onClick = {navController.navigate(LunchTrayScreen.FirstBox.title){}
        })  {
            "Counts"
        }
        Button(onClick = {navController.navigate(LunchTrayScreen.SecondBox.title){}
        })  {
            "History"
        }
        Button(onClick = {navController.navigate(LunchTrayScreen.ThirdBox.title){}
        })  {
            "Leave"
        }

        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.FirstBox.title,
            modifier = Modifier.weight(1f) // ✅ Ключевой момент без Scaffold
        ) {
            composable(LunchTrayScreen.FirstBox.title) { FirstBox() }
            composable(LunchTrayScreen.SecondBox.title) { SecondBox() }
            composable(LunchTrayScreen.ThirdBox.title) { ThirdBox() }
        }
    }
}