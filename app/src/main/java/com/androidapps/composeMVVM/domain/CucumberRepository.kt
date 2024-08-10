package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.flow.Flow

interface CucumberRepository {

    fun getEndpoint(): Flow<ApiResponse<List<GithubUserList>>>
    fun postEndpoint(): Flow<ApiResponse<List<GithubUserList>>>
    fun deleteEndpoint(): Flow<ApiResponse<List<GithubUserList>>>
}
