package com.androidapps.composeMVVM.presentation.adapter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.subscriptions.getSubscriptionsListItem
import com.androidapps.composeMVVM.presentation.viewModel.SubscriptionsViewModel

@Composable
fun SubscribeAdapter(
    userName: String, viewModel: SubscriptionsViewModel = hiltViewModel(),
) {



}

@Composable
fun FillSubsList(data: List<getSubscriptionsListItem>?) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        data?.let {
            items(it) { rowSubData ->
                FillSubsRow(rowSubData)
            }
        }

    }

}

@Composable
fun FillSubsRow(rowSubData: getSubscriptionsListItem) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .size(150.dp)
            .clickable { }
            .padding(8.dp)
    ) {
        val (card, profileImg, subsName, repo) = createRefs()

        Box(modifier = Modifier
            .background(color = Color.LightGray)
            .size(140.dp)
            .constrainAs(card) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)

            })

        AsyncImage(
            model = rowSubData.owner.avatar_url,
            contentDescription = rowSubData.url,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .aspectRatio(0.85f)
                .size(120.dp)
                .constrainAs(profileImg) {
                    start.linkTo(card.start)
                    top.linkTo(card.top)
                }
            
        )
        
        Text(text = rowSubData.owner.login, fontSize = 16.sp, color = Color.Black,
            modifier = Modifier.constrainAs(subsName) {
                start.linkTo(profileImg.end)
                top.linkTo(profileImg.bottom)
            })

    }
}
