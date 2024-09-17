package com.androidapps.composeMVVM.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.presentation.adapter.FillSubsList
import com.androidapps.composeMVVM.presentation.ui.theme.MyApplicationTheme
import com.androidapps.composeMVVM.presentation.viewModel.SubscriptionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubscriptionsActivity : ComponentActivity() {
    val viewModel: SubscriptionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userName = intent.getStringExtra("userName") ?: " "
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                viewModel.getSubscribe(userName)
                UpdateUI()
            }
        }
    }

    @Composable
    private fun UpdateUI() {
        val subsItems by viewModel.subscribe.collectAsState()
        when (subsItems) {
            is ApiResponse.Loading -> {

            }
            is ApiResponse.Success -> {
                FillSubsList(subsItems.data)
            }
            is ApiResponse.ErrorMessage -> {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    MyApplicationTheme {

    }
}