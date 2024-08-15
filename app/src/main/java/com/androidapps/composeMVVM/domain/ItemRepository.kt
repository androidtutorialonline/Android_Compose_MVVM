package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.userInfo
import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getUserList(): Flow<ApiResponse<List<GithubUserList>>>
    fun userProfile(userName: String): Flow<ApiResponse<userInfo>>
}
