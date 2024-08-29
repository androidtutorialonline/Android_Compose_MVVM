package com.androidapps.composeMVVM.app.utils

import com.androidapps.composeMVVM.domain.model.GithubUserList
import com.androidapps.composeMVVM.data.database.UserEntity

/**
 * Extension function to convert a list of [GithubUserList] objects to a list of [UserEntity] objects.
 *
 * This function maps each [GithubUserList] item to a corresponding [UserEntity] item.
 *
 * @return A list of [UserEntity] objects representing the given [GithubUserList] items.
 */
fun List<GithubUserList>.toItemEntry(): List<UserEntity> {
    return map { userList ->
        UserEntity(
            id = userList.id!!.toLong(),                        // Convert the user ID to Long for database storage
            loginName = userList.login!!,                       // User login name
            url = userList.url!!,                               // User URL
            avatarUrl = userList.avatarUrl,                     // URL to user avatar
            followersUrl = userList.followersUrl,               // URL to user followers
            followingUrl = userList.followingUrl,               // URL to users the user is following
            subscriptionsUrl = userList.subscriptionsUrl,       // URL to user subscriptions
            organizationsUrl = userList.organizationsUrl,       // URL to user organizations
            reposUrl = userList.reposUrl,                       // URL to user repositories
            type = userList.type,                               // Type of user (e.g., User or Organization)
            siteAdmin = userList.siteAdmin                      // Indicates if the user is a site admin
            // Add other properties as needed
        )
    }
}


/**
 * Extension function to convert a list of [UserEntity] objects to a list of [GithubUserList] objects.
 *
 * This function maps each [UserEntity] item to a corresponding [GithubUserList] item.
 *
 * @return A list of [GithubUserList] objects representing the given [UserEntity] items.
 */

fun List<UserEntity>.toUserList(): List<GithubUserList> {
    return map { itemEntry ->
        GithubUserList(
            id = itemEntry.id.toInt(),                   // Convert the user ID from Long to Int
            login = itemEntry.loginName,                 // User login name
            url = itemEntry.url,                         // User URL
            avatarUrl = itemEntry.avatarUrl,             // URL to user avatar
            followersUrl = itemEntry.followersUrl,       // URL to user followers
            followingUrl = itemEntry.followingUrl,       // URL to users the user is following
            subscriptionsUrl = itemEntry.subscriptionsUrl, // URL to user subscriptions
            organizationsUrl = itemEntry.organizationsUrl, // URL to user organizations
            reposUrl = itemEntry.reposUrl,               // URL to user repositories
            type = itemEntry.type,                       // Type of user (e.g., User or Organization)
            siteAdmin = itemEntry.siteAdmin              // Indicates if the user is a site admin
            // Add other properties as needed
        )
    }
}