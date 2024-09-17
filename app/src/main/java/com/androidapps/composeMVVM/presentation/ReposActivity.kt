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
import com.androidapps.composeMVVM.presentation.adapter.FillRepoList
import com.androidapps.composeMVVM.presentation.ui.theme.MyApplicationTheme
import com.androidapps.composeMVVM.presentation.viewModel.ReposViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReposActivity : ComponentActivity() {

    val viewModel: ReposViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userName = intent.getStringExtra("userName") ?: " "

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                viewModel.getRepos(userName)
                updateUI()
            }
        }
    }

    @Composable
    private fun updateUI() {
        val repoItems by viewModel.repos.collectAsState()
        when (repoItems) {
            is ApiResponse.Loading -> {

            }
            is ApiResponse.Success -> {
                FillRepoList(repoItems.data)
            }
            is ApiResponse.ErrorMessage -> {

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    MyApplicationTheme {

    }
}