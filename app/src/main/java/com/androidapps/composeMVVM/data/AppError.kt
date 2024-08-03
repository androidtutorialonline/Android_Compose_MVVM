package com.androidapps.composeMVVM.data

sealed class AppError : Exception() {
    object InternetError : AppError()
    object NetworkError : AppError()
    object DatabaseError : AppError()
    data class UnknownError(override val message: String?) : AppError()
}

