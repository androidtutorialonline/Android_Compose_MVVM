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
import com.androidapps.composeMVVM.presentation.adapter.FollowerAdapter
import com.androidapps.composeMVVM.presentation.adapter.fillFollowerList
import com.androidapps.composeMVVM.presentation.ui.theme.MyApplicationTheme
import com.androidapps.composeMVVM.presentation.viewModel.FollowersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowersActivity : ComponentActivity() {

    val viewModel: FollowersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the data passed from the first activity
        val userName = intent.getStringExtra("userName") ?: " "
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                viewModel.getFollower(userName)
                UpdateUI()
            //FollowerAdapter()
            }
        }
    }

    @Composable
    private fun UpdateUI() {
        val itemsData by viewModel.follower.collectAsState()
        val context = LocalContext.current

        when (itemsData) {
            is ApiResponse.Loading -> {

            }

            is ApiResponse.Success -> {
                FollowerAdapter(list = itemsData.data)
                //FillFollowerList(list = itemsData.data)

            }

            is ApiResponse.ErrorMessage -> {

            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    MyApplicationTheme {
        fillFollowerList()
    }
}