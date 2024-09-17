package com.androidapps.composeMVVM.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.androidapps.composeMVVM.R
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.UserInfo
import com.androidapps.composeMVVM.presentation.ui.theme.MyApplicationTheme
import com.androidapps.composeMVVM.presentation.ui.theme.event
import com.androidapps.composeMVVM.presentation.ui.theme.follower
import com.androidapps.composeMVVM.presentation.ui.theme.repo
import com.androidapps.composeMVVM.presentation.ui.theme.subscribe
import com.androidapps.composeMVVM.presentation.viewModel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfileActivity : ComponentActivity() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the data passed from the first activity
        val userName = intent.getStringExtra("userName") ?: " "
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                viewModel.getUserProfile(userName)
                UpdateUI()
            }
        }
    }

    @Composable
    private fun UpdateUI() {
        val userProfile by viewModel.userProfile.collectAsState()

        when (userProfile) {
            is ApiResponse.Loading -> {

            }

            is ApiResponse.Success -> {
                val data = (userProfile as ApiResponse.Success<UserInfo>).data
                data?.let {
                    UserProfileInfo(it, this)
                }
            }

            is ApiResponse.ErrorMessage -> {

            }
        }
    }
}

@Composable
fun Buttons(userInfo: UserInfo, mActivity: Activity) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Column(
            Modifier
                .weight(0.5f)
                .height(170.dp)
                .padding(10.dp)
                .clickable {
                    val intent = Intent(mActivity, FollowersActivity::class.java).apply {
                        putExtra("userName", userInfo.login)
                    }
                    mActivity.startActivity(intent)
                }
                .background(
                    color = follower,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.followers),
                contentDescription = null,
                modifier = Modifier
                    .height(65.dp)
                    .width(65.dp)
            )

            Text(
                text = "followers " + userInfo.followers.toString(),
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Column(
            Modifier
                .weight(0.5f)
                .height(170.dp)
                .padding(10.dp)
                .clickable {
                    val intent = Intent(mActivity, ReposActivity::class.java).apply {
                        putExtra("userName", userInfo.login)
                    }
                    mActivity.startActivity(intent)
                }
                .background(
                    color = repo,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.following),
                contentDescription = null,
                modifier = Modifier
                    .height(65.dp)
                    .width(65.dp)
            )

            Text(
                text = "Repo " + userInfo.public_repos.toString(),
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Column(
            Modifier
                .weight(0.5f)
                .height(170.dp)
                .padding(10.dp)
                .clickable {
                    val intent = Intent(mActivity, ReceivedEventActivity::class.java).apply {
                        putExtra("userName", userInfo.login)
                    }
                    mActivity.startActivity(intent)
                }
                .background(
                    color = event,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(
                    id = R.drawable.star_rate
                ),
                contentDescription = null,
                modifier = Modifier
                    .height(65.dp)
                    .width(65.dp)
            )

            Text(
                text = "Received Events ",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Column(
            Modifier
                .weight(0.5f)
                .height(170.dp)
                .padding(10.dp)
                .clickable {
                    val intent = Intent(mActivity, SubscriptionsActivity::class.java).apply {
                        putExtra("userName", userInfo.login)
                    }
                    mActivity.startActivity(intent)

                }
                .background(
                    color = subscribe,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.subscriptions),
                contentDescription = null,
                modifier = Modifier
                    .height(65.dp)
                    .width(65.dp)
            )

            Text(
                text = "subscriptions ",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
private fun UserProfileInfo(userInfo: UserInfo, mActivity: Activity = ComponentActivity()) {

    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(android.graphics.Color.parseColor("#f2f1f6"))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(
            Modifier
                .height(250.dp)
                .background(color = Color(android.graphics.Color.parseColor("#32357a")))
        ) {
            val (topImg, profile, title, back, pen) = createRefs()

            Image(painterResource(id = R.drawable.arc_3),
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .constrainAs(topImg) {
                        bottom.linkTo(parent.bottom)
                    })

            GlobalAsyncImage(
                imageUrl = userInfo.avatar_url!!,
                contentDescription = userInfo.url,
                modifier = Modifier
                    .constrainAs(profile) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Text(text = userInfo.name ?: "Profile",
                style = TextStyle(color = Color.Green, fontSize = 30.sp),
                modifier = Modifier.constrainAs(title) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 32.dp)
                })

            Image(painterResource(id = R.drawable.back),
                contentDescription = null,
                Modifier
                    .clickable {

                    }
                    .constrainAs(back) {
                        top.linkTo(parent.top, margin = 24.dp)
                        start.linkTo(parent.start, margin = 24.dp)

                    })
        }

        Text(
            text = userInfo.login ?: "", fontSize = 26.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            color = Color(android.graphics.Color.parseColor("#32357a"))
        )

        var userBio = ""
        if (userInfo.blog != "") {
            userBio = userInfo.blog ?: ""
        }

        Text(
            text = userBio,
            fontSize = 18.sp,
            color = Color(android.graphics.Color.parseColor("#747679"))
        )
        Buttons(userInfo, mActivity)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyToolbarWithNav() {
    TopAppBar(
        title = {
            Text(text = "My Toolbar with Navigation")
        },
        navigationIcon = {
            IconButton(onClick = { /* Handle back press */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { /* Do something */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
            IconButton(onClick = { /* Do something */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyApplicationTheme {
        UserProfileInfo(UserInfo())
    }
}