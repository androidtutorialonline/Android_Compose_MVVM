package com.androidapps.composeMVVM.app.utils

import com.androidapps.composeMVVM.domain.model.GithubUserList
import com.androidapps.composeMVVM.data.database.UserEntity

fun List<GithubUserList>.toItemEntry(): List<UserEntity> {
    return map { userList ->
        UserEntity(
            id = userList.id!!.toLong(),
            name = userList.login!!,
            description = userList.url!!
            // Add other properties as needed
        )
    }
}
fun List<UserEntity>.toUserList(): List<GithubUserList> {
    return map { itemEntry ->
        GithubUserList(
            id = itemEntry.id.toInt(),
            login = itemEntry.name,
            url = itemEntry.description
            // Add other properties as needed
        )
    }
}