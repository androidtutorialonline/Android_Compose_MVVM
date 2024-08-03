package com.androidapps.composeMVVM.presentation.viewModel

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapps.composeMVVM.app.utils.NetworkConnection
import com.androidapps.composeMVVM.data.AppError
import com.androidapps.composeMVVM.domain.model.GetUserListItem
import com.androidapps.composeMVVM.data.Resource
import com.androidapps.composeMVVM.domain.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val networkConnection: NetworkConnection?
) : ViewModel() {


    private val _userInfo = MutableStateFlow<Resource<List<GetUserListItem>>>(Resource.success(emptyList()))
    val userInfo: StateFlow<Resource<List<GetUserListItem>>> = _userInfo.asStateFlow()

    private val _errorMessage = MutableLiveData<AppError?>()
    val errorMessage: LiveData<AppError?> = _errorMessage

    init {
        getUserList()
    }

    private fun getUserList() = viewModelScope.launch {

        try {

            //if (networkConnection?.isOnline()!!) {
                _userInfo.value = Resource.loading(null)

                getUserUseCase()
                    .catch {
                        Timber.e("", " error occurred")
                        _errorMessage.value = AppError.UnknownError("")
                    }
                    .collect { itemList ->
                    _userInfo.value = Resource.success(itemList)
                    _errorMessage.value = null // Clear previous errors
                }

            /*} else {
                Timber.i("NetworkConnection", "Internet is not connected.")
                _errorMessage.value = AppError.InternetError
            }*/
        } catch (e: IOException) {
            Timber.e(e, "Network error occurred")
            _errorMessage.value = AppError.NetworkError
        } catch (e: SQLiteException) {
            Timber.e(e, "Database error occurred")
            _errorMessage.value = AppError.DatabaseError
        } catch (e: Exception) {
            Timber.e(e, "Unknown error occurred")
            _errorMessage.value = AppError.UnknownError(e.message)
        }
    }


}
