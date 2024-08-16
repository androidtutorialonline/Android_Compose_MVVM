package com.androidapps.composeMVVM.data.repository

import android.app.Application
import android.content.Context
import com.androidapps.composeMVVM.R
import com.androidapps.composeMVVM.app.utils.RetryAPI
import com.androidapps.composeMVVM.app.utils.toItemEntry
import com.androidapps.composeMVVM.app.utils.toUserList
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.ApiService
import com.androidapps.composeMVVM.data.database.ItemDao
import com.androidapps.composeMVVM.data.model.followers.getFollowerList
import com.androidapps.composeMVVM.data.model.followers.getFollowerListItem
import com.androidapps.composeMVVM.data.model.receivedEvents.receivedEventsListItem
import com.androidapps.composeMVVM.data.model.subscriptions.getSubscriptionsListItem
import com.androidapps.composeMVVM.data.model.userInfo
import com.androidapps.composeMVVM.data.model.userRepo.getUserRepoItem
import com.androidapps.composeMVVM.domain.ItemRepository
import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val itemDao: ItemDao,
    private val context: Application
) : ItemRepository {

    override fun getUserList(): Flow<ApiResponse<List<GithubUserList>>> = flow {
        try {
            emit(ApiResponse.Loading())
            // Fetch items from the API
            RetryAPI.retry {
                // Synchronous call using suspend function
                val response = apiService.getUserList()
                if (response.isSuccessful) {
                    // Access the status code
                    val statusCode = response.code()
                    val mUserList = response.body()
                    // Insert fetched items into the local database
                    itemDao.insertItems(mUserList?.toItemEntry()!!)
                    // Emit all items from the local database as a flow
                    itemDao.getAllItems()
                        .catch {
                            emit(emptyList())
                        }
                        .collect { list ->
                            emit(ApiResponse.Success(list.toUserList(), statusCode))
                            Timber.i(context.getString(R.string.api_success_response, response))
                        }
                } else {
                    // Handle error response
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
            // Handle exceptions and possibly emit an empty list or an error state

            emit(ApiResponse.ErrorMessage(e.message ?: "", 0))
            // Log the error or handle it accordingly
            Timber.e(e, "Failed to fetch items")
        }
    }.flowOn(Dispatchers.IO)

    override fun userProfile(userName: String): Flow<ApiResponse<userInfo>> =
        flow {
            try {
                emit(ApiResponse.Loading())
                RetryAPI.retry {
                    val response = apiService.getUserProfile(userName)
                    val statusCode = response.code()
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        emit(ApiResponse.Success(data = responseData, statusCode = statusCode))
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

    override fun getFollowers(
        userName: String,
    ): Flow<ApiResponse<List<getFollowerListItem>>> =
        flow {
            try {
                emit(ApiResponse.Loading())
                RetryAPI.retry {
                    val response = apiService.getFollowers(userName)
                    val statusCode = response.code()
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        emit(ApiResponse.Success(data = responseData, statusCode = statusCode))
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

    override fun getSubscriptions(
        userName: String,
    ): Flow<ApiResponse<List<getSubscriptionsListItem>>> =
        flow {
            try {
                emit(ApiResponse.Loading())
                RetryAPI.retry {
                    val response = apiService.getSubscriptions(userName)
                    val statusCode = response.code()
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        emit(ApiResponse.Success(data = responseData, statusCode = statusCode))
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

    override fun getReceivedEvents(
        userName: String,
    ): Flow<ApiResponse<List<receivedEventsListItem>>> =
        flow {
            try {
                RetryAPI.retry {
                    val response = apiService.getReceivedEvents(userName)
                    val statusCode = response.code()
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        emit(ApiResponse.Success(data = responseData, statusCode = statusCode))
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

    override fun getUserRepo(
        userName: String,
    ): Flow<ApiResponse<List<getUserRepoItem>>> =
        flow {
            try {
                RetryAPI.retry {
                    val response = apiService.getUserRepo(userName)
                    val statusCode = response.code()
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        emit(ApiResponse.Success(data = responseData, statusCode = statusCode))
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

