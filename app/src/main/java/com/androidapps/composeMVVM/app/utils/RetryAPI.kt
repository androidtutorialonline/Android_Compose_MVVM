package com.androidapps.composeMVVM.app.utils

import kotlinx.coroutines.delay
import timber.log.Timber

/**
 * Utility class for retrying operations with exponential backoff.
 * Provides a mechanism to retry a block of code a specified number of times
 * with increasing delay between attempts.
 */
class RetryAPI {



    companion object {
        /**
         * Executes a block of code with retry logic and exponential backoff.
         *
         * @param times Number of retry attempts. The first attempt is made without delay.
         * @param initialDelay Initial delay before retrying the block, in milliseconds.
         * @param maxDelay Maximum delay between retries, in milliseconds.
         * @param factor Exponential factor to increase the delay after each retry.
         * @param block The suspend function to be executed and retried if it throws an exception.
         * @return The result of the block function if it succeeds within the retry attempts.
         * @throws Exception If the block continues to fail after the specified retry attempts.
         */
        suspend fun <T> retry(
            times: Int = 1,
            initialDelay: Long = 1000, // 1 second
            maxDelay: Long = 3000,     // 3 seconds
            factor: Double = 2.0,
            block: suspend () -> T,
        ): T {
            var currentDelay = initialDelay
            repeat(times - 1) {
                try {
                    // Attempt to execute the block
                    return block()
                } catch (e: Exception) {
                    // Log the exception and retry after a delay
                    Timber.e(e, "Attempt failed, retrying in $currentDelay ms")
                }
                // Delay before the next attempt, using exponential backoff
                delay(currentDelay)
                currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
            }
            // Last attempt if all previous attempts fail
            return block() // last attempt
        }
    }


}