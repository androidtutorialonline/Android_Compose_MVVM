package com.androidapps.composeMVVM.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapps.composeMVVM.app.utils.NetworkConnection
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.receivedEvents.ReceivedEventsListItem
import com.androidapps.composeMVVM.domain.ReceivedEventUseCause
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceivedEventViewModel @Inject constructor(
    private val useCause: ReceivedEventUseCause,
    private val networkConnection: NetworkConnection
): ViewModel() {

    private val _receivedEvent = MutableStateFlow<ApiResponse<List<ReceivedEventsListItem>>>(ApiResponse.Success(
        emptyList(), 0
    ))
    val receivedEvent: StateFlow<ApiResponse<List<ReceivedEventsListItem>>> = _receivedEvent.asStateFlow()

    fun getReceivedEvent(userName: String) = viewModelScope.launch{
        try {
            networkConnection.let {
                if(it.isOnline()) {
                    useCause(userName).collect { receivedEvent ->
                        _receivedEvent.value = receivedEvent
                    }
                } else {

                }
            }

        } catch (e: Exception) {

        }
    }

}