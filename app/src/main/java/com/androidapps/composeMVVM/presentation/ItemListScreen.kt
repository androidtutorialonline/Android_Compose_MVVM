package com.androidapps.composeMVVM.presentation


import android.R.id
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.androidapps.composeMVVM.R
import com.androidapps.composeMVVM.data.AppError
import com.androidapps.composeMVVM.data.StatusCalled
import com.androidapps.composeMVVM.domain.model.GetUserListItem
import com.androidapps.composeMVVM.presentation.viewModel.ItemViewModel
import timber.log.Timber


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

    // Define the onItemClick function
    val context = LocalContext.current
    val onItemClick: (GetUserListItem) -> Unit = { item ->
        Toast.makeText(context, "Clicked: ${item.login}", Toast.LENGTH_SHORT).show()

        //AlertDialogExample()
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
                // Safely handle the nullable items
                items.data?.let { data ->
                    // If data is not null, display the list
                    extracted(data, onItemClick)
                } ?: run {
                    // Optionally, show a placeholder if data is null
                    Text(text = "No data available", modifier = Modifier.padding(16.dp))
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
fun extracted(
    it: List<GetUserListItem>,
    onItemClick: (GetUserListItem) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /*(
                count = movies.itemCount,
                key = movies.itemKey { it.id },
            )*/
        items(it) { item ->

            Timber.e("Item Data$item")
            gitUserItem(userInfo = item, onItemClick)

            /*Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(imageVector = Icons.Default.Star, contentDescription = null)
                Spacer(modifier = Modifier.width(80.dp))
                Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) }
                    .padding(16.dp)
                Text(text = item.login!!, style = MaterialTheme.typography.headlineLarge)
                Text(text = item.url!!, style = MaterialTheme.typography.bodyMedium)

            }*/

        }
    }
}

@Composable
fun gitUserItem(userInfo: GetUserListItem,
                onItemClick: (GetUserListItem) -> Unit) {

    var selectedItem by remember { mutableStateOf<GetUserListItem?>(null) }

    ConstraintLayout(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                //onItemClick(userInfo)
                selectedItem = userInfo
            }
            .height(180.dp)
            .padding(vertical = 4.dp),
    ) {
        val (cover, card) = createRefs()

        Box(
            modifier = Modifier
                .height(170.dp)
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                },
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .fillMaxWidth()
                    .padding(start = 150.dp,
                        top = 45.dp),
            ) {
                Text(
                    text = userInfo.login!!,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    color = Color(0xFF415BE9),
                    text = userInfo.url!!,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }

        AsyncImage(
            model = userInfo.avatarUrl,
            placeholder = painterResource(id = R.drawable.image_picture_icon), // Use your drawable resource
            error = painterResource(id = R.drawable.error_icon), // Use the same drawable for error

            contentDescription = userInfo.url,
            modifier = Modifier
                .width(120.dp)
                .aspectRatio(0.85f)
                .constrainAs(cover) {
                    start.linkTo(card.start, 8.dp)
                    bottom.linkTo(card.bottom, 18.dp)
                },
        )
    }
    selectedItem?.let {
        AlertDialog(
            onDismissRequest = { selectedItem = null },
            confirmButton = {
                TextButton(onClick = { selectedItem = null }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { selectedItem = null }) {
                    Text("Cancel")
                }
            },
            title = {
                Text(text = "Item Clicked", fontSize = 20.sp)
            },
            text = {
                Text("You clicked on ${it.login} .")
            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        )
    }

}


