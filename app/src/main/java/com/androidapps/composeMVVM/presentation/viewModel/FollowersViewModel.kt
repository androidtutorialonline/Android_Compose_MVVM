package com.androidapps.composeMVVM.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapps.composeMVVM.app.utils.NetworkConnection
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.followers.getFollowerListItem
import com.androidapps.composeMVVM.domain.FollowersUseCause
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val followersUseCause: FollowersUseCause,
    private val networkConnection: NetworkConnection,
) : ViewModel() {

    val _follower = MutableStateFlow<ApiResponse<List<getFollowerListItem>>>(
        ApiResponse.Success(
            emptyList(), 0
        )
    )

    var follower: StateFlow<ApiResponse<List<getFollowerListItem>>> = _follower.asStateFlow()

     fun getFollower(userName: String) = viewModelScope.launch {
        try {
            networkConnection.let {
                if (it.isOnline()) {
                    followersUseCause(userName).collect { followerData ->
                        _follower.value = followerData
                    }
                } else {
                    Timber.e("")
                }
            }
        } catch (e: Exception) {
            Timber.e("")
        }
    }

}