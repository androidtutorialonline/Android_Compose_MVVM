package com.androidapps.composeMVVM.data

import com.androidapps.composeMVVM.domain.model.GithubUserList
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    suspend fun getUserList(): List<GithubUserList>

}
