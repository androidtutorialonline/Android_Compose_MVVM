package com.androidapps.composeMVVM.data.repository

import com.androidapps.composeMVVM.data.database.ItemDao
import com.androidapps.composeMVVM.domain.ItemRepository
import com.androidapps.composeMVVM.app.utils.toItemEntry
import com.androidapps.composeMVVM.app.utils.toUserList
import com.androidapps.composeMVVM.data.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val itemDao: ItemDao,
) : ItemRepository {

    override fun getUserList() = flow {
        try {
            //emit(ApiResponse.Loading)
            // Fetch items from the API
            val items = retry {
                apiService.getUserList()
            }
            // Insert fetched items into the local database
            itemDao.insertItems(items.toItemEntry())

            // Emit all items from the local database as a flow
            itemDao.getAllItems()
                .catch {
                    emit(emptyList())
                }
                .collect { list ->
                    emit(list.toUserList())
                }
        } catch (e: Exception) {
            // Handle exceptions and possibly emit an empty list or an error state
            emit(emptyList()) // Or you can use a Result wrapper to emit a failure state
            // Log the error or handle it accordingly
            Timber.e(e, "Failed to fetch items")
        }

        //emit(value = apiService.getUserList())
    }.flowOn(Dispatchers.IO)


    private suspend fun <T> retry(
        times: Int = 1,
        initialDelay: Long = 1000, // 1 second
        maxDelay: Long = 3000,     // 3 seconds
        factor: Double = 2.0,
        block: suspend () -> T,
    ): T {
        var currentDelay = initialDelay
        repeat(times - 1) {
            try {
                return block()
            } catch (e: Exception) {
                Timber.e(e, "Attempt failed, retrying in $currentDelay ms")
            }
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
        }
        return block() // last attempt
    }
}

