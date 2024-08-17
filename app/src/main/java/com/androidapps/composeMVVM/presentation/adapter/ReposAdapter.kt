package com.androidapps.composeMVVM.presentation.adapter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.receivedEvents.Repo
import com.androidapps.composeMVVM.presentation.viewModel.ReposViewModel

@Composable
fun ReposAdapter(userName: String, viewModel: ReposViewModel = hiltViewModel()) {

}

@Composable
fun FillRepoList(repoItems: List<Repo>?) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        repoItems?.let {
            items(it) { rowData ->
                FillRepoRow(rowData)    
            }
            
        }

    }

}

@Composable
fun FillRepoRow(rowData: Repo = Repo()) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .clickable { }
            .size(150.dp)
            .padding(8.dp)
    ) {
        val (card, profileImg, repoName, userName, repoURL, type, desc, licence, count) = createRefs()
        
        Box(
            modifier = Modifier
                .height(140.dp)
                .background(color = Color(android.graphics.Color.GRAY))
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) 

        
        Text(text = rowData.url ?: "null", fontSize = 16.sp, color = Color.Black,
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(repoURL) {
                    start.linkTo(card.start, 8.dp)
                    top.linkTo(card.top, 8.dp)
                })

        var license = ""
        rowData.license?.let {
               license = "Name " + it.name + " url " + it.url
        }

        Text(text = license, fontSize = 14.sp, color = Color.Black,
            modifier = Modifier.constrainAs(licence) {
                start.linkTo(repoURL.start)
                top.linkTo(repoURL.bottom)
            })
        
    }


}
