package com.androidapps.composeMVVM.data

/**
 * A sealed class representing different types of errors that can occur in the application.
 *
 * This class provides a structured way to handle various error scenarios, including network-related
 * issues, database errors, and unknown errors. It extends `Exception` to integrate seamlessly with
 * Kotlin's exception handling mechanism.
 */
sealed class AppError : Exception() {

    /**
     * Represents an error related to internet connectivity issues.
     */
    object InternetError : AppError()

    /**
     * Represents a general network error, such as timeouts or connectivity problems.
     */
    object NetworkError : AppError()

    /**
     * Represents an error related to database operations, such as failed queries or connection issues.
     */
    object DatabaseError : AppError()

    /**
     * Represents an unknown error with a customizable message.
     *
     * @param message A descriptive message about the error. This allows for providing more context
     *                about what went wrong.
     */
    data class UnknownError(override val message: String?) : AppError()
}


