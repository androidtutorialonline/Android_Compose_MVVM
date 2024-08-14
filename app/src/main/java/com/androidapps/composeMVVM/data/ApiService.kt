package com.androidapps.composeMVVM.data

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
    suspend fun getUserRepo(@Path("username") repo: String?): Response<List<GithubUserList>>

    @GET("/users/{username}")
    suspend fun getUserInfo(@Path("username") repo: String?): Response<List<GithubUserList>>

    @GET("/users/{username}/followers")
    suspend fun getFollowers(@Path("username") repo: String?): Response<List<GithubUserList>>

    @GET("/users/{username}/subscriptions")
    suspend fun getsubscriptions(@Path("username") repo: String?): Response<List<GithubUserList>>

    @GET("/users/{username}/received_events")
    suspend fun getReceivedEvents(@Path("username") repo: String?): Response<List<GithubUserList>>

    @GET("/orgs/{username}")
    suspend fun getOrgs(@Path("username") repo: String?): Response<List<GithubUserList>>

    @GET("/repos/{username}/redwood")
    suspend fun getUserRepoInfo(@Path("username") repo: String?): Response<List<GithubUserList>>

}
