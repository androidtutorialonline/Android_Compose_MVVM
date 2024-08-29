package com.androidapps.composeMVVM.presentation.viewModel

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapps.composeMVVM.app.utils.NetworkConnection
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.AppError
import com.androidapps.composeMVVM.domain.GetUserUseCase
import com.androidapps.composeMVVM.domain.model.GithubUserList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel // Annotation to allow Hilt to provide dependencies to this ViewModel
class ItemViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,// Use case for fetching GitHub users
    private val networkConnection: NetworkConnection?,// Utility to check network status
) : ViewModel() {
    // Mutable state holding the list of GitHub users and loading/error status
    private val _userInfo =
        MutableStateFlow<ApiResponse<List<GithubUserList>>>(ApiResponse.Success(emptyList(), 0))

    // Publicly exposed state to be observed by the UI
    val userInfo: StateFlow<ApiResponse<List<GithubUserList>>> = _userInfo.asStateFlow()

    // Mutable LiveData to hold error messages
    private val _errorMessage = MutableLiveData<AppError?>()

    // Publicly exposed LiveData for observing error messages in the UI
    val errorMessage: LiveData<AppError?> = _errorMessage

    // Initialize the ViewModel by calling the method to fetch the user list
    init {
        getUserList()
    }

    // Method to fetch the user list from the use case
    private fun getUserList() = viewModelScope.launch {
        try {
            // Check if the device is connected to the internet
            if (networkConnection?.isOnline() == true) {
                // Collect the flow of user list data from the use case
                getUserUseCase().collect { itemList ->
                    // Update the user list in the state flow
                    _userInfo.value = itemList
                }
            } else {
                // If there is no internet connection, show an internet error message
                Timber.i("NetworkConnection", "Internet is not connected.")
                _errorMessage.value = AppError.InternetError
            }
        } catch (e: IOException) {
            // Handle network errors such as timeouts or other IO issues
            Timber.e(e, "Network error occurred")
            _errorMessage.value = AppError.NetworkError
        } catch (e: SQLiteException) {
            // Handle database errors, if any
            Timber.e(e, "Database error occurred")
            _errorMessage.value = AppError.DatabaseError
        } catch (e: Exception) {
            // Catch any unknown errors and log them
            Timber.e(e, "Unknown error occurred")
            _errorMessage.value = AppError.UnknownError(e.message)
        }
    }
}
