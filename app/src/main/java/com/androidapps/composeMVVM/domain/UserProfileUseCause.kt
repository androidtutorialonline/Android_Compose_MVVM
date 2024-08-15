package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.userInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileUseCause @Inject constructor(private val repository: ItemRepository) {

    operator fun invoke(userName: String): Flow<ApiResponse<userInfo>> {
        return repository.userProfile(userName)
    }

}