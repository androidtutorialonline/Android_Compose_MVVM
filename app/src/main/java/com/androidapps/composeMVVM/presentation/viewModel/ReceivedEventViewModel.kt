package com.androidapps.composeMVVM.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.androidapps.composeMVVM.app.utils.NetworkConnection
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.receivedEvents.receivedEventsListItem
import com.androidapps.composeMVVM.domain.ReceivedEventUseCause
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ReceivedEventViewModel @Inject constructor(
    private val useCause: ReceivedEventUseCause,
    private val networkConnection: NetworkConnection
): ViewModel() {

    val _receivedEvent = MutableStateFlow<ApiResponse<List<receivedEventsListItem>>>(ApiResponse.Success(
        emptyList(), 0
    ))
    val receivedEvent: StateFlow<ApiResponse<List<receivedEventsListItem>>> = _receivedEvent.asStateFlow()

    fun getReceivedEvent() {
        try {
            networkConnection.let {
                if(it.isOnline()) {

                } else {

                }
            }

        } catch (e: Exception) {

        }
    }

}