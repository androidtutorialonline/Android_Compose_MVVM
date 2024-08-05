package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getUserList(): Flow<List<GithubUserList>>
}
