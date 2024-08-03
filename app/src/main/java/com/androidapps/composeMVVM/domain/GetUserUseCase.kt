package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.domain.model.GetUserListItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetUserUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    open operator fun invoke(): Flow<List<GetUserListItem>> = repository.getUserList()
}
