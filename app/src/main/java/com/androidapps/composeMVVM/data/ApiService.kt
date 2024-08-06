package com.androidapps.composeMVVM.data

import com.androidapps.composeMVVM.domain.model.GithubUserList
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/users")
    suspend fun getUserList(): List<GithubUserList>

    @GET("/repos/{user}/{repo}")
    suspend fun getEndpoint(@Path("repo") repo: String?): Call<Any?>?

    @POST("/repos/{user}/{repo}")
    suspend fun postEndpoint(@Path("repo") repo: String?): Call<Any?>?

    @DELETE("/repos/{user}/{repo}")
    suspend fun deleteEndpoint(@Path("repo") repo: String?): Call<Any?>?

}
