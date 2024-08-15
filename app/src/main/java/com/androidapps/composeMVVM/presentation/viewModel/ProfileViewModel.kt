package com.androidapps.composeMVVM.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapps.composeMVVM.domain.UserProfileUseCause
import dagger.hilt.android.lifecycle.HiltViewModel

import com.androidapps.composeMVVM.app.utils.NetworkConnection
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.userInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCase: UserProfileUseCause,
    private val network: NetworkConnection?,
) : ViewModel() {

    private val _userProfile =
        MutableStateFlow<ApiResponse<userInfo>>(ApiResponse.Success(userInfo(), 0))
    val userProfile: StateFlow<ApiResponse<userInfo>> = _userProfile.asStateFlow()

    fun getUserProfile(userName: String) = viewModelScope.launch {
        try {
            network?.let {
                if (it.isOnline()) {
                    userUseCase(userName).collect { userProfileData ->
                        _userProfile.value = userProfileData
                    }
                } else {

                    Timber.e("")
                }
            }

        } catch (e: Exception) {

        }


    }

}