package com.androidapps.composeMVVM.data

import com.androidapps.composeMVVM.data.model.followers.GetFollowerListItem
import com.androidapps.composeMVVM.data.model.receivedEvents.ReceivedEventsListItem
import com.androidapps.composeMVVM.data.model.receivedEvents.Repo
import com.androidapps.composeMVVM.data.model.subscriptions.GetSubscriptionsListItem
import com.androidapps.composeMVVM.data.model.UserInfo
import com.androidapps.composeMVVM.domain.model.GithubUserList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface defining the API service for making network requests to the GitHub API.
 * Each function represents a different API endpoint, returning a Retrofit `Response` object.
 */

interface ApiService {

    /**
     * Fetches the list of GitHub users.
     * @return a `Response` object containing a list of [GithubUserList].
     */
    @GET("/users")
    suspend fun getUserList(): Response<List<GithubUserList>>

    /**
     * Fetches the repositories of a specific user.
     * @param userName the username of the GitHub user.
     * @return a `Response` object containing a list of [Repo].
     */
    @GET("/users/{username}/repos")
    suspend fun getUserRepo(@Path("username") userName: String?): Response<List<Repo>>

    /**
     * Fetches the profile information of a specific user.
     * @param username the username of the GitHub user.
     * @return a `Response` object containing the [UserInfo] of the user.
     */
    @GET("/users/{username}")
    suspend fun getUserProfile(@Path("username") username: String): Response<UserInfo>

    /**
     * Fetches the list of followers for a specific user.
     * @param username the username of the GitHub user.
     * @return a `Response` object containing a list of [GetFollowerListItem].
     */
    @GET("/users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): Response<List<GetFollowerListItem>>

    /**
     * Fetches the subscriptions (repositories the user is watching) of a specific user.
     * @param username the username of the GitHub user.
     * @return a `Response` object containing a list of [GetSubscriptionsListItem].
     */
    @GET("/users/{username}/subscriptions")
    suspend fun getSubscriptions(@Path("username") username: String): Response<List<GetSubscriptionsListItem>>

    /**
     * Fetches the list of events received by a specific user.
     * @param username the username of the GitHub user.
     * @return a `Response` object containing a list of [ReceivedEventsListItem].
     */
    @GET("/users/{username}/received_events")
    suspend fun getReceivedEvents(@Path("username") username: String): Response<List<ReceivedEventsListItem>>

    /**
     * Fetches the organizations a specific user belongs to.
     * @param username the username of the GitHub user.
     * @return a `Response` object containing a list of [GithubUserList].
     */
    @GET("/orgs/{username}")
    suspend fun getOrgs(@Path("username") username: String): Response<List<GithubUserList>>

    /**
     * Fetches detailed repository information for a specific repository of a user.
     * @param username the username of the GitHub user.
     * @return a `Response` object containing the [UserInfo] of the user.
     */
    @GET("/repos/{username}/redwood")
    suspend fun getUserRepoInfo(@Path("username") username: String): Response<UserInfo>

}
