package com.androidapps.composeMVVM.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data class representing a GitHub user in the application.
 *
 * This class is used to map JSON responses from the GitHub API to the application's data model.
 *
 * @property avatarUrl The URL to the user's avatar.
 * @property eventsUrl The URL to the user's events.
 * @property followersUrl The URL to the user's followers.
 * @property followingUrl The URL to the users the user is following.
 * @property gistsUrl The URL to the user's gists.
 * @property gravatarId The user's Gravatar ID.
 * @property htmlUrl The URL to the user's GitHub profile.
 * @property id The unique identifier for the user.
 * @property login The user's login name.
 * @property nodeId The node ID of the user.
 * @property organizationsUrl The URL to the organizations the user belongs to.
 * @property receivedEventsUrl The URL to the events received by the user.
 * @property reposUrl The URL to the user's repositories.
 * @property siteAdmin Indicates if the user is a GitHub site administrator.
 * @property starredUrl The URL to the user's starred repositories.
 * @property subscriptionsUrl The URL to the user's subscriptions.
 * @property type The type of user (e.g., User or Organization).
 * @property url The URL to the user profile.
 * @property isCheck A boolean flag used for internal application logic (e.g., to track selection state).
 */
@JsonClass(generateAdapter = true)
data class GithubUserList(
    @Json(name = "avatar_url")
    val avatarUrl: String? = "", // URL to user's avatar

    @Json(name = "events_url")
    val eventsUrl: String? = "", // URL to user's events

    @Json(name = "followers_url")
    val followersUrl: String? = "", // URL to user's followers

    @Json(name = "following_url")
    val followingUrl: String? = "", // URL to users the user is following

    @Json(name = "gists_url")
    val gistsUrl: String? = "", // URL to user's gists

    @Json(name = "gravatar_id")
    val gravatarId: String? = "", // User's Gravatar ID

    @Json(name = "html_url")
    val htmlUrl: String? = "", // URL to user's GitHub profile

    @Json(name = "id")
    val id: Int? = 0, // Unique user ID

    @Json(name = "login")
    var login: String? = "", // User's login name

    @Json(name = "node_id")
    val nodeId: String? = "", // Node ID of the user

    @Json(name = "organizations_url")
    val organizationsUrl: String? = "", // URL to organizations the user belongs to

    @Json(name = "received_events_url")
    val receivedEventsUrl: String? = "", // URL to events received by the user

    @Json(name = "repos_url")
    val reposUrl: String? = "", // URL to user's repositories

    @Json(name = "site_admin")
    val siteAdmin: Boolean? = false, // Indicates if the user is a site admin

    @Json(name = "starred_url")
    val starredUrl: String? = "", // URL to user's starred repositories

    @Json(name = "subscriptions_url")
    val subscriptionsUrl: String? = "", // URL to user's subscriptions

    @Json(name = "type")
    val type: String? = "", // Type of user (e.g., User or Organization)

    @Json(name = "url")
    val url: String? = "", // URL to user profile

    var isCheck: Boolean = false // Internal flag for tracking selection state
)
