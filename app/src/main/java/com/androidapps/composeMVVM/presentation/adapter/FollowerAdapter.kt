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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.androidapps.composeMVVM.data.model.followers.getFollowerListItem
import com.androidapps.composeMVVM.presentation.viewModel.FollowersViewModel


@Composable
fun FollowerAdapter(
    viewModel: FollowersViewModel = hiltViewModel(),
    list: List<getFollowerListItem>?
) {
    FillFollowerList(list = list)
    /*val itemsData by viewModel.follower.collectAsState()
    val context = LocalContext.current

    when (itemsData) {
        is ApiResponse.Loading -> {

        }

        is ApiResponse.Success -> {
            FillFollowerList(list = itemsData.data)

        }

        is ApiResponse.ErrorMessage -> {

        }
    }*/

}

@Composable
fun FillFollowerList(list: List<getFollowerListItem>?) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        list?.let {
            items(it) { itemData ->
                fillFollowerList(itemData)
            }
        }
    }
}

@Composable
fun fillFollowerList(itemData: getFollowerListItem = getFollowerListItem()) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .clickable { }
            .padding(4.dp)
            .size(150.dp)

    ) {

        val (card, profileImg, userName) = createRefs()

        Box(
            modifier = Modifier
                .height(150.dp)
                .background(color = Color(android.graphics.Color.GRAY))
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
        ) {

        }

        AsyncImage(model = itemData.avatar_url, contentDescription = itemData.followers_url,
            modifier = Modifier
                .size(120.dp)
                .width(120.dp)
                .clip(CircleShape)
                .aspectRatio(0.85f)
                .constrainAs(profileImg) {
                    start.linkTo(card.start, 8.dp)
                    top.linkTo(card.top, 8.dp)
                })

        Text(text = itemData.login ?: "Profile Name",
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp)
                .constrainAs(userName) {
                top.linkTo(profileImg.top)
                start.linkTo(profileImg.end)
            })
    }
}
