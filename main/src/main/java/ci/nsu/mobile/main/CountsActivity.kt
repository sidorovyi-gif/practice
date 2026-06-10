@file:Suppress("DEPRECATION")

package ci.nsu.mobile.main

import android.R.attr.onClick
import android.R.attr.text
import android.app.Activity
import android.app.LauncherActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ci.nsu.mobile.main.screens.CountsSecond
import ci.nsu.mobile.main.ui.ui.theme.PracticeTheme

class CountsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme() {
                CountScreen()
            }
        }
    }
}

sealed class LunchTrayScreen(val route: String) {
    data object CountsPlus : LunchTrayScreen("countsplus")
    data object Counts : LunchTrayScreen("counts")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountScreen() {
    var receivedText by remember { mutableStateOf("") }
    val context = LocalContext.current
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text(receivedText) }, navigationIcon = {
                IconButton(onClick = {
                    val intent = Intent(context, MainActivity::class.java).apply{}
                    if (context is Activity) {
                        context.finish()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Blue, titleContentColor = Color.White
            )
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)  // ← отступ под TopAppBar
                .padding(16.dp),         // ← отступ по краям
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            var deposit by remember { mutableStateOf("") }
            var term by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // ─── Поле 1: Стартовый взнос ───
                TextField(
                    value = deposit,
                    onValueChange = { deposit = it },
                    label = { Text("Стартовый взнос (₽)") },
                    placeholder = { Text("Например: 100000") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // ─── Поле 2: Срок вклада ───
                TextField(
                    value = term,
                    onValueChange = { term = it },
                    label = { Text("Срок вклада (месяцев)") },
                    placeholder = { Text("Например: 12") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(onClick = { navController.navigate(LunchTrayScreen.CountsPlus.route){}

                }
                ){
                    Text("Доп. параметры")
                }

                NavHost(navController = navController, startDestination = LunchTrayScreen.Counts.route, modifier = Modifier.padding(innerPadding)) {
                    composable(LunchTrayScreen.CountsPlus.route) {
                        CountsSecond(term.toInt())
                    }
                    composable(LunchTrayScreen.Counts.route) {
                        Blank()
                    }
                }
            }
        }
    }
}

@Composable
fun Blank(){
    Surface(){}
}
