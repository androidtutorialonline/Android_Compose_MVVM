package com.androidapps.composeMVVM

import com.androidapps.composeMVVM.domain.model.GithubUserList
import com.androidapps.composeMVVM.domain.GetUserUseCase
import com.androidapps.composeMVVM.domain.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeGetUserUseCase(private val items: List<GithubUserList>, repository: ItemRepository) :
    GetUserUseCase(repository) {
    override fun invoke(): Flow<List<GithubUserList>> {
        return flowOf(items)
    }
}
