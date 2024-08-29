package com.androidapps.composeMVVM.data

/**
 * Enum class representing the different states of a network or data request.
 *
 * This enum is used to denote the status of operations, such as API calls or data fetching processes.
 * It helps in managing and reacting to the current state of these operations in a clear and consistent way.
 */
enum class StatusCalled {
    /**
     * Indicates that the request was successful and data has been received.
     */
    SUCCESS,

    /**
     * Indicates that an error occurred during the request, and no data was received.
     */
    ERROR,

    /**
     * Indicates that the request is currently in progress and data has not yet been received.
     */
    LOADING
}
