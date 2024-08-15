package com.androidapps.composeMVVM.data.repository

import com.androidapps.composeMVVM.app.utils.RetryAPI
import com.androidapps.composeMVVM.app.utils.toItemEntry
import com.androidapps.composeMVVM.app.utils.toUserList
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.ApiService
import com.androidapps.composeMVVM.data.database.ItemDao
import com.androidapps.composeMVVM.data.model.userInfo
import com.androidapps.composeMVVM.domain.ItemRepository
import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val itemDao: ItemDao,
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

    override fun userProfile(userName: String): Flow<ApiResponse<userInfo>> = flow {
        try {
            emit(ApiResponse.Loading())
            RetryAPI.retry {
                val response = apiService.getUserProfile(userName)
                if (response.isSuccessful) {
                    val status = response.code()
                    val userProfile = response.body()
                    emit(ApiResponse.Success(data = userProfile, statusCode = status))
                } else {
                    // Handle error response
                    val statusCode = response.code()
                    emit(
                        ApiResponse.ErrorMessage<userInfo>(
                            "Error: ${response.message()}",
                            statusCode
                        )
                    )
                }
            }
        } catch (e: Exception) {
            emit(ApiResponse.ErrorMessage(e.message?: "", 0))
            Timber.e(e, e.message)
        }
    }.flowOn(Dispatchers.IO)
}

