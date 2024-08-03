package com.androidapps.composeMVVM.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androidapps.composeMVVM.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        /*setContent {
            MyListApp()
        }*/
    }
}

@Composable
fun MyListApp() {
    ItemListScreen()
}

/*@Composable
fun MyButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Click Me")
    }
}

@Preview
@Composable
fun PreviewMyButton() {
    MyButton(onClick = {
        // Handle click event here
        println("Button clicked!")
    })
}*/


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}