package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.followers.getFollowerListItem
import com.androidapps.composeMVVM.data.model.receivedEvents.ReceivedEventsListItem
import com.androidapps.composeMVVM.data.model.receivedEvents.Repo
import com.androidapps.composeMVVM.data.model.subscriptions.getSubscriptionsListItem
import com.androidapps.composeMVVM.data.model.userInfo
import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getUserList(): Flow<ApiResponse<List<GithubUserList>>>
    fun userProfile(userName: String): Flow<ApiResponse<userInfo>>

    fun getFollowers(userName: String): Flow<ApiResponse<List<getFollowerListItem>>>
    fun getSubscriptions(userName: String): Flow<ApiResponse<List<getSubscriptionsListItem>>>
    fun getReceivedEvents(userName: String): Flow<ApiResponse<List<ReceivedEventsListItem>>>
    fun getUserRepo(userName: String): Flow<ApiResponse<List<Repo>>>
}
