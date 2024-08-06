package com.androidapps.composeMVVM.app.utils

import com.androidapps.composeMVVM.domain.model.GithubUserList
import com.androidapps.composeMVVM.data.database.UserEntity

fun List<GithubUserList>.toItemEntry(): List<UserEntity> {
    return map { userList ->
        UserEntity(
            id = userList.id!!.toLong(),
            loginName = userList.login!!,
            url = userList.url!!,
            avatarUrl = userList.avatarUrl,
            followersUrl = userList.followersUrl,
            followingUrl = userList.followingUrl,
            subscriptionsUrl = userList.subscriptionsUrl,
            organizationsUrl = userList.organizationsUrl,
            reposUrl = userList.reposUrl,
            type = userList.type,
            siteAdmin = userList.siteAdmin
            // Add other properties as needed
        )
    }
}
fun List<UserEntity>.toUserList(): List<GithubUserList> {
    return map { itemEntry ->
        GithubUserList(
            id = itemEntry.id.toInt(),
            login = itemEntry.loginName,
            url = itemEntry.url,
            avatarUrl = itemEntry.avatarUrl,
            followersUrl = itemEntry.followersUrl,
            followingUrl = itemEntry.followingUrl,
            subscriptionsUrl = itemEntry.subscriptionsUrl,
            organizationsUrl = itemEntry.organizationsUrl,
            reposUrl = itemEntry.reposUrl,
            type = itemEntry.type,
            siteAdmin = itemEntry.siteAdmin,
            // Add other properties as needed
        )
    }
}