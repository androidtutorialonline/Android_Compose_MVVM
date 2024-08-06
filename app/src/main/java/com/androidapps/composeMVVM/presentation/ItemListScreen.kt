package com.androidapps.composeMVVM.presentation


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.draw.clip
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
import com.androidapps.composeMVVM.domain.model.GithubUserList
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
    val onItemClick: (GithubUserList) -> Unit = { item ->
        Toast.makeText(context, "Clicked: ${item.login}", Toast.LENGTH_SHORT).show()
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
                    FillListView(data, onItemClick)
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
fun FillListView(
    it: List<GithubUserList>,
    onItemClick: (GithubUserList) -> Unit,
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
        }
    }
}

@Composable
fun gitUserItem(
    userInfo: GithubUserList,
    onItemClick: (GithubUserList) -> Unit,
) {

    var selectedItem by remember { mutableStateOf<GithubUserList?>(null) }

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
        val (cover, card, data) = createRefs()


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

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
                .constrainAs(data) {
                    start.linkTo(cover.end)
                    top.linkTo(cover.top)
                },
        ) {
            KeyValueDisplay(key = "User Name: ", value = userInfo.login ?: "")
            KeyValueDisplay(key = "URL: ", value = userInfo.url ?: "", color = Color(0xFF415BE9))
        }


        AsyncImage(
            model = userInfo.avatarUrl,
            placeholder = painterResource(id = R.drawable.image_picture_icon), // Use your drawable resource
            error = painterResource(id = R.drawable.error_icon), // Use the same drawable for error

            contentDescription = userInfo.url,
            modifier = Modifier
                .size(118.dp) // Size of the image
                .clip(CircleShape) // Clip image to a circular shape
                .width(120.dp)
                .aspectRatio(0.85f)
                .constrainAs(cover) {
                    start.linkTo(card.start, 8.dp)
                    bottom.linkTo(card.bottom, 8.dp)
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

@Composable
fun KeyValueDisplay(key: String, value: String, color: Color = Color.Black) {

    Text(
        text = key,
        style = MaterialTheme.typography.titleLarge,
        color = color
    )

    Spacer(modifier = Modifier.width(8.dp))
    Text(
        text = value,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = color
    )
}


