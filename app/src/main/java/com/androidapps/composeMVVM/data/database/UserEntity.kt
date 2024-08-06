package com.androidapps.composeMVVM.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class UserEntity(
    @PrimaryKey val id: Long,
    val loginName: String?,
    val url: String?,
    val avatarUrl: String?,
    val followersUrl: String?,
    val followingUrl: String?,
    val subscriptionsUrl: String?,
    val organizationsUrl: String?,
    val reposUrl: String?,
    val type: String?,
    val siteAdmin: Boolean?
)

