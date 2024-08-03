package com.androidapps.composeMVVM.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.androidapps.composeMVVM.data.AppError
import com.androidapps.composeMVVM.domain.model.GetUserListItem
import com.androidapps.composeMVVM.data.StatusCalled
import com.androidapps.composeMVVM.presentation.viewModel.ItemViewModel

@Composable
fun ItemListScreen(viewModel: ItemViewModel = hiltViewModel()) {
    val items by viewModel.userInfo.collectAsState()
    val errorMessage by viewModel.errorMessage.observeAsState()

    val errorText = when (errorMessage) {
        is AppError.NetworkError -> "Network error. Please check your connection."
        is AppError.InternetError -> "Internet is not working. Please check your connection."
        is AppError.DatabaseError -> "Database error occurred."
        is AppError.UnknownError -> "An unexpected error occurred: ${(errorMessage as AppError.UnknownError).message}"
        else -> null
    }

    if (errorText != null) {
        // Display an error message to the user
        Text(
            text = errorText,
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )
    } else {

        when (items.status) {
            StatusCalled.SUCCESS -> {
                val it = items.data!!
                LazyColumn {
                    items(it) { item ->
                        ItemRow(item = item)
                    }
                }
            }

            StatusCalled.LOADING -> {
                //mBinding.progress.visibility = View.VISIBLE
            }

            StatusCalled.ERROR -> {
                //mBinding.progress.visibility = View.GONE
            }
        }
    }
}

@Composable
fun ItemRow(item: GetUserListItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = item.login!!, style = MaterialTheme.typography.headlineLarge)
        Text(text = item.url!!, style = MaterialTheme.typography.bodyMedium)
    }
}
