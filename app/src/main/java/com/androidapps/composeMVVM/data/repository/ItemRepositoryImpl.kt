package com.androidapps.composeMVVM.data.repository

import android.app.Application
import com.androidapps.composeMVVM.R
import com.androidapps.composeMVVM.app.utils.RetryAPI
import com.androidapps.composeMVVM.app.utils.toItemEntry
import com.androidapps.composeMVVM.app.utils.toUserList
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.ApiService
import com.androidapps.composeMVVM.data.database.ItemDao
import com.androidapps.composeMVVM.data.model.followers.GetFollowerListItem
import com.androidapps.composeMVVM.data.model.receivedEvents.ReceivedEventsListItem
import com.androidapps.composeMVVM.data.model.receivedEvents.Repo
import com.androidapps.composeMVVM.data.model.subscriptions.GetSubscriptionsListItem
import com.androidapps.composeMVVM.data.model.UserInfo
import com.androidapps.composeMVVM.domain.ItemRepository
import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
/**
 * Concrete implementation of the `ItemRepository` interface.
 *
 * This class is responsible for fetching data from the network and local database,
 * handling network errors, retry logic, and updating the database with the latest data.
 * It also provides various methods for retrieving user-related information such as
 * profiles, followers, subscriptions, and events.
 *
 * @param apiService API service for making network requests.
 * @param itemDao DAO for local database access.
 * @param context Application context for accessing resources.
 */
class ItemRepositoryImpl @Inject constructor(
    private val apiService: ApiService,     // API service for making network requests
    private val itemDao: ItemDao,           // DAO for database access
    private val context: Application,        // Application context for accessing resources
) : ItemRepository {

    /**
     * Fetches a list of GitHub users from the network and updates the local database.
     * The method uses a retry mechanism for network requests and handles errors accordingly.
     *
     * @return A Flow emitting ApiResponse containing a list of GitHub users.
     */
    override fun getUserList(): Flow<ApiResponse<List<GithubUserList>>> = flow {
        try {
            emit(ApiResponse.Loading()) // Emit loading state before starting the network request

            // Retry mechanism for network requests
            RetryAPI.retry {
                val response = apiService.getUserList()
                if (response.isSuccessful) {
                    val statusCode = response.code()
                    val mUserList = response.body()

                    // Insert the fetched user list into the local database
                    itemDao.insertItems(mUserList?.toItemEntry()!!)

                    // Emit all items from the local database
                    itemDao.getAllItems()
                        .catch {
                            emit(emptyList())  // Emit an empty list if there's an error reading from the DB
                        }
                        .collect { list ->
                            emit(
                                ApiResponse.Success(
                                    list.toUserList(),
                                    statusCode
                                )
                            ) // Emit the success state with the fetched user list
                            Timber.i(context.getString(R.string.api_success_response, response))
                        }
                } else {
                    // Handle non-successful response
                    val statusCode = response.code()
                    emit(
                        ApiResponse.ErrorMessage<List<GithubUserList>>(
                            "Error: ${response.message()}",
                            statusCode
                        )
                    )
                }
            }
        } catch (e: Exception) {
            // Handle any exception during the network request
            emit(ApiResponse.ErrorMessage(e.message ?: "", 0))
            Timber.e(e, "Failed to fetch items")
        }
    }.flowOn(Dispatchers.IO) // Perform the operation in a background thread

    /**
     * Fetches detailed profile information of a specific GitHub user.
     *
     * @param userName The username of the GitHub user.
     * @return A Flow emitting ApiResponse containing the user's profile information.
     */
    override fun userProfile(userName: String): Flow<ApiResponse<UserInfo>> =
        flow {
            try {
                emit(ApiResponse.Loading()) // Emit loading state
                RetryAPI.retry {
                    val response = apiService.getUserProfile(userName)
                    val statusCode = response.code()
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        emit(
                            ApiResponse.Success(
                                data = responseData,
                                statusCode = statusCode
                            )
                        ) // Emit success state with profile data
                        Timber.i(context.getString(R.string.api_success_response, response))
                    } else {
                        // Handle error response
                        emit(
                            ApiResponse.ErrorMessage(
                                message = context.getString(
                                    R.string.api_failure_response,
                                    response
                                ), statusCode = statusCode
                            )
                        )
                        Timber.e(context.getString(R.string.api_failure_response, response))
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.ErrorMessage(e.message ?: "", 0))
                Timber.e(e, e.message)
            }
        }.flowOn(Dispatchers.IO)

    /**
     * Fetches the list of followers for a specific GitHub user.
     *
     * @param userName The username of the GitHub user.
     * @return A Flow emitting ApiResponse containing a list of followers.
     */
    override fun getFollowers(
        userName: String,
    ): Flow<ApiResponse<List<GetFollowerListItem>>> =
        flow {
            try {
                emit(ApiResponse.Loading()) // Emit loading state
                RetryAPI.retry {
                    val response = apiService.getFollowers(userName)
                    val statusCode = response.code()
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        emit(ApiResponse.Success(data = responseData, statusCode = statusCode)) // Emit success state with follower list
                        Timber.i(context.getString(R.string.api_success_response, response))
                    } else {
                        // Handle error response
                        emit(
                            ApiResponse.ErrorMessage(
                                message = context.getString(
                                    R.string.api_failure_response,
                                    response
                                ), statusCode = statusCode
                            )
                        )
                        Timber.e(context.getString(R.string.api_failure_response, response))
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.ErrorMessage(e.message ?: "", 0))
                Timber.e("API error : " + e.message)
            }
        }.flowOn(Dispatchers.IO)

    /**
     * Fetches the list of repositories the user is subscribed to.
     *
     * @param userName The username of the GitHub user.
     * @return A Flow emitting ApiResponse containing a list of subscriptions.
     */
    override fun getSubscriptions(
        userName: String,
    ): Flow<ApiResponse<List<GetSubscriptionsListItem>>> =
        flow {
            try {
                emit(ApiResponse.Loading()) // Emit loading state
                RetryAPI.retry {
                    val response = apiService.getSubscriptions(userName)
                    val statusCode = response.code()
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        emit(ApiResponse.Success(data = responseData, statusCode = statusCode)) // Emit success state with subscription list
                        Timber.i(context.getString(R.string.api_success_response, response))
                    } else {
                        // Handle error response
                        emit(
                            ApiResponse.ErrorMessage(
                                message = context.getString(
                                    R.string.api_failure_response,
                                    response
                                ), statusCode = statusCode
                            )
                        )
                        Timber.e(context.getString(R.string.api_failure_response, response))
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.ErrorMessage(e.message ?: "Error", 0))
                Timber.e("API error : " + e.message)
            }
        }.flowOn(Dispatchers.IO)

    /**
     * Fetches the list of events received by a specific GitHub user.
     *
     * @param userName The username of the GitHub user.
     * @return A Flow emitting ApiResponse containing a list of received events.
     */
    override fun getReceivedEvents(
        userName: String,
    ): Flow<ApiResponse<List<ReceivedEventsListItem>>> =
        flow {
            try {
                RetryAPI.retry {
                    val response = apiService.getReceivedEvents(userName)
                    val statusCode = response.code()
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        emit(ApiResponse.Success(data = responseData, statusCode = statusCode)) // Emit success state with received events
                        Timber.i(context.getString(R.string.api_success_response, response))
                    } else {
                        // Handle error response
                        emit(
                            ApiResponse.ErrorMessage(
                                message = context.getString(
                                    R.string.api_failure_response,
                                    response
                                ), statusCode = statusCode
                            )
                        )
                        Timber.e(context.getString(R.string.api_failure_response, response))
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.ErrorMessage("", 0))
                Timber.e("Api error :" + e.message)
            }
        }.flowOn(Dispatchers.IO)

    /**
     * Fetches the list of repositories for a specific GitHub user.
     *
     * @param userName The username of the GitHub user.
     * @return A Flow emitting ApiResponse containing a list of repositories.
     */
    override fun getUserRepo(
        userName: String,
    ): Flow<ApiResponse<List<Repo>>> =
        flow {
            try {
                RetryAPI.retry {
                    val response = apiService.getUserRepo(userName)
                    val statusCode = response.code()
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        emit(ApiResponse.Success(data = responseData, statusCode = statusCode)) // Emit success state with repositories
                        Timber.i(context.getString(R.string.api_success_response, response))
                    } else {
                        // Handle error response
                        emit(
                            ApiResponse.ErrorMessage(
                                message = context.getString(
                                    R.string.api_failure_response,
                                    response
                                ), statusCode = statusCode
                            )
                        )
                        Timber.e(context.getString(R.string.api_failure_response, response))
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.ErrorMessage("", 0))
                Timber.e("Api error :" + e.message)
            }
        }.flowOn(Dispatchers.IO)
}

