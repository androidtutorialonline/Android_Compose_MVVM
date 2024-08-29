package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.followers.getFollowerListItem
import com.androidapps.composeMVVM.data.model.receivedEvents.ReceivedEventsListItem
import com.androidapps.composeMVVM.data.model.receivedEvents.Repo
import com.androidapps.composeMVVM.data.model.subscriptions.getSubscriptionsListItem
import com.androidapps.composeMVVM.data.model.userInfo
import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface defining data fetching operations related to GitHub users.
 *
 * This interface abstracts the data sources and provides methods to fetch user-related data from
 * various sources such as the network or local database.
 */
interface ItemRepository {

    /**
     * Fetches a list of GitHub users.
     *
     * @return A [Flow] of [ApiResponse] containing a list of [GithubUserList] objects.
     *         The response can be in different states: [ApiResponse.Loading], [ApiResponse.Success], or [ApiResponse.ErrorMessage].
     */
    fun getUserList(): Flow<ApiResponse<List<GithubUserList>>>

    /**
     * Fetches detailed profile information for a specific GitHub user.
     *
     * @param userName The username of the GitHub user.
     * @return A [Flow] of [ApiResponse] containing [userInfo] for the specified user.
     */
    fun userProfile(userName: String): Flow<ApiResponse<userInfo>>

    /**
     * Fetches a list of followers for a specific GitHub user.
     *
     * @param userName The username of the GitHub user.
     * @return A [Flow] of [ApiResponse] containing a list of [getFollowerListItem] objects.
     */
    fun getFollowers(userName: String): Flow<ApiResponse<List<getFollowerListItem>>>

    /**
     * Fetches the subscription list (repositories the user is subscribed to) for a specific GitHub user.
     *
     * @param userName The username of the GitHub user.
     * @return A [Flow] of [ApiResponse] containing a list of [getSubscriptionsListItem] objects.
     */
    fun getSubscriptions(userName: String): Flow<ApiResponse<List<getSubscriptionsListItem>>>

    /**
     * Fetches a list of received events (activity such as stars, forks) for a specific GitHub user.
     *
     * @param userName The username of the GitHub user.
     * @return A [Flow] of [ApiResponse] containing a list of [ReceivedEventsListItem] objects.
     */
    fun getReceivedEvents(userName: String): Flow<ApiResponse<List<ReceivedEventsListItem>>>

    /**
     * Fetches a list of repositories for a specific GitHub user.
     *
     * @param userName The username of the GitHub user.
     * @return A [Flow] of [ApiResponse] containing a list of [Repo] objects.
     */
    fun getUserRepo(userName: String): Flow<ApiResponse<List<Repo>>>
}

