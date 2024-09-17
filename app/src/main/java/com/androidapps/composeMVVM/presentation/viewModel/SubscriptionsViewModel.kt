package com.androidapps.composeMVVM.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapps.composeMVVM.app.utils.NetworkConnection
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.subscriptions.GetSubscriptionsListItem
import com.androidapps.composeMVVM.domain.SubscriptionsUseCause
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscriptionsViewModel @Inject constructor(
    private val useCause: SubscriptionsUseCause,
    private val networkConnection: NetworkConnection
): ViewModel() {

    private val _subscribe = MutableStateFlow<ApiResponse<List<GetSubscriptionsListItem>>>(ApiResponse.Success(emptyList(), 0))
    val subscribe: StateFlow<ApiResponse<List<GetSubscriptionsListItem>>> = _subscribe.asStateFlow()

    fun getSubscribe(userName: String) = viewModelScope.launch {
        try {
            networkConnection.let {
                if(it.isOnline()) {
                    useCause(userName).collect { subscribeList ->
                        _subscribe.value = subscribeList
                    }
                } else {

                }
            }
        } catch (e: Exception) {

        }
    }

}