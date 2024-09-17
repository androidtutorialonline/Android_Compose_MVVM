package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.subscriptions.GetSubscriptionsListItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class SubscriptionsUseCause @Inject constructor(
    private val repository: ItemRepository
) {
    open operator fun invoke(userName: String): Flow<ApiResponse<List<GetSubscriptionsListItem>>> {
        return repository.getSubscriptions(userName)
    }
}