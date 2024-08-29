package com.androidapps.composeMVVM.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a user entity in the local database.
 *
 * This data class maps to the "items" table in the database and defines the schema for user data storage.
 *
 * @Entity annotation marks this class as an entity for Room, with "items" as the table name.
 *
 * @property id Unique identifier for the user. This is the primary key for the table.
 * @property loginName The login name or username of the user.
 * @property url The URL to the user's profile.
 * @property avatarUrl The URL to the user's avatar image.
 * @property followersUrl The URL to the list of the user's followers.
 * @property followingUrl The URL to the list of users the user is following.
 * @property subscriptionsUrl The URL to the list of repositories the user is subscribed to.
 * @property organizationsUrl The URL to the list of organizations the user belongs to.
 * @property reposUrl The URL to the list of repositories owned by the user.
 * @property type The type of the user (e.g., "User", "Organization").
 * @property siteAdmin Boolean flag indicating if the user is a site administrator.
 */
@Entity(tableName = "items")
data class UserEntity(
    @PrimaryKey val id: Long,
    val loginName: String?,
    val url: String,
    val avatarUrl: String? = "",
    val followersUrl: String? = "",
    val followingUrl: String? = "",
    val subscriptionsUrl: String? = "",
    val organizationsUrl: String? = "",
    val reposUrl: String? = "",
    val type: String? = "",
    val siteAdmin: Boolean? = false
)


