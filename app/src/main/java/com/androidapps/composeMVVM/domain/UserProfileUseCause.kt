package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.UserInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileUseCause @Inject constructor(private val repository: ItemRepository) {

    operator fun invoke(userName: String): Flow<ApiResponse<UserInfo>> {
        return repository.userProfile(userName)
    }

}