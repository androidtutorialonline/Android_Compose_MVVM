package com.androidapps.composeMVVM.data

import com.androidapps.composeMVVM.data.model.followers.getFollowerListItem
import com.androidapps.composeMVVM.data.model.receivedEvents.receivedEventsListItem
import com.androidapps.composeMVVM.data.model.subscriptions.getSubscriptionsListItem
import com.androidapps.composeMVVM.data.model.userInfo
import com.androidapps.composeMVVM.data.model.userRepo.getUserRepoItem
import com.androidapps.composeMVVM.domain.model.GithubUserList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/users")
    suspend fun getUserList(): Response<List<GithubUserList>>

    @GET("/users/{username}/repos")
    suspend fun getUserRepo(@Path("username") userName: String?): Response<List<getUserRepoItem>>

    @GET("/users/{username}")
    suspend fun getUserProfile(@Path("username") username: String): Response<userInfo>

    @GET("/users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): Response<List<getFollowerListItem>>

    @GET("/users/{username}/subscriptions")
    suspend fun getSubscriptions(@Path("username") username: String): Response<List<getSubscriptionsListItem>>

    @GET("/users/{username}/received_events")
    suspend fun getReceivedEvents(@Path("username") username: String): Response<List<receivedEventsListItem>>

    @GET("/orgs/{username}")
    suspend fun getOrgs(@Path("username") username: String): Response<List<GithubUserList>>

    @GET("/repos/{username}/redwood")
    suspend fun getUserRepoInfo(@Path("username") username: String): Response<userInfo>

}
