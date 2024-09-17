package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.followers.GetFollowerListItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class FollowersUseCause @Inject constructor(
    private val repository: ItemRepository,
) {
    open operator fun invoke(userName: String): Flow<ApiResponse<List<GetFollowerListItem>>> {
        return repository.getFollowers(userName)
    }
}