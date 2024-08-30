package com.androidapps.composeMVVM.presentation.adapter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.receivedEvents.ReceivedEventsListItem
import com.androidapps.composeMVVM.presentation.viewModel.ReceivedEventViewModel

@Composable
fun ReceivedEventAdapter(userName: String, viewModel: ReceivedEventViewModel = hiltViewModel()) {


}

@Composable
fun FillReceivedList(itemsData: List<ReceivedEventsListItem>?) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        itemsData?.let {
            items(it) { rowData ->
                FillReceivedRow(data = rowData)
            }
        }
    }

}

@Composable
fun FillReceivedRow(data: ReceivedEventsListItem = ReceivedEventsListItem()) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .clickable { }
            .size(150.dp)
            .padding(8.dp)

    ) {
        val (card, profileImg, userName, type, repo) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
                .height(140.dp)
                .background(color = Color(android.graphics.Color.GRAY))

        )

        AsyncImage(model = data.actor?.avatar_url, contentDescription = data.actor?.url,
            modifier = Modifier
                .constrainAs(profileImg) {
                    start.linkTo(card.start)
                    top.linkTo(card.top)
                }
                .clip(CircleShape)
                .aspectRatio(0.85f)
        )

        Text(text = data.actor?.login?: "null",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .constrainAs(userName) {
                    start.linkTo(profileImg.end, 8.dp)
                    top.linkTo(profileImg.top, 8.dp)
                }
        )

        Text(text = data.type, fontSize = 14.sp, color = Color.Blue,
            modifier = Modifier.constrainAs(type) {
                start.linkTo(profileImg.end, 8.dp)
                top.linkTo(userName.bottom, 8.dp)
            })

        Text(text = data.repo?.name ?: "Repo", fontSize = 12.sp, color = Color.DarkGray,
            modifier = Modifier.constrainAs(repo) {
                start.linkTo(profileImg.end, 8.dp)
                top.linkTo(type.bottom, 8.dp)
            })
    }
}
