package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.subscriptions.getSubscriptionsListItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class SubscriptionsUseCause @Inject constructor(
    private val repository: ItemRepository
) {
    open operator fun invoke(): Flow<ApiResponse<List<getSubscriptionsListItem>>> {
        return repository.getSubscriptions("")
    }
}