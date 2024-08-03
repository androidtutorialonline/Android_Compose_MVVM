package com.androidapps.composeMVVM.data

import com.androidapps.composeMVVM.domain.model.GetUserListItem
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    suspend fun getUserList(): List<GetUserListItem>

}
