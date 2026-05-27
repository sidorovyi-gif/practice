package ci.nsu.mobile.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainActivityScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainActivityScreen(modifier: Modifier) {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current as? Activity
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                val intent = Intent(context, CountsActivity::class.java).apply{
                    putExtra("text_data", text)
                }
                context?.startActivity(intent)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Counts")
        }
        Button(
            onClick = {
                val intent = Intent(context, HistoryActivity::class.java).apply{
                    putExtra("text_data", text)
                }
                context?.startActivity(intent)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("History")
        }
        Button(
            onClick = {
                (context as? Activity)?.finishAndRemoveTask()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Exit")
        }
    }
}