package com.androidapps.composeMVVM.data

/*
sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val exception: Throwable) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}*/

/**
 * A sealed class representing the state of a network or data operation.
 *
 * This class encapsulates the different states that can occur when making a request
 * or performing an operation, such as loading, success, or error. It allows for a
 * more structured and descriptive way to handle the results of such operations.
 *
 * @param T The type of the data being returned in the successful response.
 *
 * @property data The data returned by a successful response. Nullable, as not all responses
 *                will include data.
 * @property statusCode The HTTP status code of the response. Nullable, as not all responses
 *                      will include a status code.
 * @property message An optional message describing an error. Nullable, as not all responses
 *                    will include a message.
 */
sealed class ApiResponse<T>(
    val data: T? = null,
    val statusCode: Int? = null,
    val message: String? = null
) {
    /**
     * Represents a successful response with data.
     *
     * @param data The data returned by the successful operation.
     * @param statusCode The HTTP status code of the response.
     */
    class Success<T>(data: T?, statusCode: Int) : ApiResponse<T>(data, statusCode)

    /**
     * Represents an error response with an optional error message.
     *
     * @param message A descriptive message about the error.
     * @param statusCode The HTTP status code of the error response. Nullable.
     */
    class ErrorMessage<T>(message: String, statusCode: Int?) : ApiResponse<T>(null, statusCode, message)

    /**
     * Represents a loading state when an operation is in progress.
     *
     * This class is used to indicate that data is being fetched and is not yet available.
     */
    class Loading<T> : ApiResponse<T>()
}
