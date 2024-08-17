package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.receivedEvents.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class ReposUseCause @Inject constructor(
    private val repository: ItemRepository
) {
    open operator fun invoke(userName: String): Flow<ApiResponse<List<Repo>>> {
        return repository.getUserRepo(userName)
    }
}