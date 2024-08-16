package com.androidapps.composeMVVM.domain

import android.content.Context
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.followers.getFollowerListItem
import com.androidapps.composeMVVM.data.model.receivedEvents.receivedEventsListItem
import com.androidapps.composeMVVM.data.model.subscriptions.getSubscriptionsListItem
import com.androidapps.composeMVVM.data.model.userInfo
import com.androidapps.composeMVVM.data.model.userRepo.getUserRepoItem
import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Path

interface ItemRepository {

    fun getUserList(): Flow<ApiResponse<List<GithubUserList>>>
    fun userProfile(userName: String): Flow<ApiResponse<userInfo>>

    fun getFollowers(userName: String): Flow<ApiResponse<List<getFollowerListItem>>>
    fun getSubscriptions(userName: String): Flow<ApiResponse<List<getSubscriptionsListItem>>>
    fun getReceivedEvents(userName: String): Flow<ApiResponse<List<receivedEventsListItem>>>
    fun getUserRepo(userName: String): Flow<ApiResponse<List<getUserRepoItem>>>
}
