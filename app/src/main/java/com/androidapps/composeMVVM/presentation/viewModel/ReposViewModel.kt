package com.androidapps.composeMVVM.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapps.composeMVVM.app.utils.NetworkConnection
import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.data.model.receivedEvents.Repo
import com.androidapps.composeMVVM.domain.ReposUseCause
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val useCause: ReposUseCause,
    private val networkConnection: NetworkConnection
): ViewModel() {
    private val _repos = MutableStateFlow<ApiResponse<List<Repo>>>(ApiResponse.Success(
        emptyList(), 0
    ))
    val repos: StateFlow<ApiResponse<List<Repo>>> = _repos.asStateFlow()

    fun getRepos(userName: String) = viewModelScope.launch {
        try {
            networkConnection.let {
                if(it.isOnline()) {
                    useCause(userName).collect { reposList ->
                        _repos.value = reposList
                    }
                } else {

                }
            }

        } catch (e: Exception) {

        }
    }

}