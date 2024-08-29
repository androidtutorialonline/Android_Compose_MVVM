package com.androidapps.composeMVVM.domain

import com.androidapps.composeMVVM.data.ApiResponse
import com.androidapps.composeMVVM.domain.model.GithubUserList
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

/**
 * Use case class for fetching a list of GitHub users.
 *
 * This class encapsulates the logic for retrieving user data from the repository and
 * exposing it as a flow of [ApiResponse]. It acts as an intermediary between the
 * presentation layer and the data layer, ensuring that the necessary data is available
 * for the UI.
 *
 * @property repository The repository responsible for data operations.
 */
open class GetUserUseCase @Inject constructor(
    private val repository: ItemRepository // Repository responsible for fetching data
) {
    /**
     * Invokes the use case to retrieve the list of GitHub users.
     *
     * This function triggers the repository to fetch the user list and returns it as a
     * [Flow] of [ApiResponse] which can be observed for updates. The [ApiResponse] wrapper
     * is used to handle loading, success, and error states.
     *
     * @return A [Flow] emitting [ApiResponse] containing a list of [GithubUserList].
     */
    open operator fun invoke(): Flow<ApiResponse<List<GithubUserList>>> {

        // Fetch the user list from the repository and return it as a Flow
        return repository.getUserList()
    }
}
