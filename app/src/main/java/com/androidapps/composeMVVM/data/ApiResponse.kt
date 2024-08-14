package com.androidapps.composeMVVM.data

/*
sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val exception: Throwable) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}*/

sealed class ApiResponse<T>(
    val data: T? = null,
    val statusCode: Int? = null,
    val message: String? = null
) {
    class Success<T>(data: T?, statusCode: Int) : ApiResponse<T>(data, statusCode)
    class ErrorMessage<T>(message: String, statusCode: Int?) : ApiResponse<T>(null, statusCode, message)
    class Loading<T> : ApiResponse<T>()
}
