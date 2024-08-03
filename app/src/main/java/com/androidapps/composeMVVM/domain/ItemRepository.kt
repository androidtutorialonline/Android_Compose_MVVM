package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.domain.model.GetUserListItem
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getUserList(): Flow<List<GetUserListItem>>
}
