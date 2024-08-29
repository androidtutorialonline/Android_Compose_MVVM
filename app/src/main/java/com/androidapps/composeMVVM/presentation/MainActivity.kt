package com.androidapps.composeMVVM.presentation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.androidapps.composeMVVM.presentation.adapter.ItemListScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main entry point for the application. This activity sets up the Hilt dependency injection
 * and provides the Compose UI content.
 */
@AndroidEntryPoint // Annotation to enable Hilt for dependency injection
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge mode for modern UI appearance
        enableEdgeToEdge()
        // Set the Compose UI content for the activity
        setContent {
            // Call the custom MyListApp composable to display the list screen
            MyListApp()
        }
    }
}

/**
 * Composable function to set up the application's main UI.
 */
@Composable
fun MyListApp() {
    // Display the ItemListScreen composable
    ItemListScreen()
}

