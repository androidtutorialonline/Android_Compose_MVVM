package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.receivedEvents.receivedEventsListItem
import com.androidapps.composeMVVM.data.model.userRepo.getUserRepoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class ReceivedEventUseCause @Inject constructor(
    private val repository: ItemRepository
) {
    open operator fun invoke(): Flow<ApiResponse<List<receivedEventsListItem>>> {
        return repository.getReceivedEvents("")
    }
}