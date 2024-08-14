package com.androidapps.composeMVVM.app.utils

import kotlinx.coroutines.delay
import timber.log.Timber

class RetryAPI {



    companion object {

        public suspend fun <T> retry(
            times: Int = 1,
            initialDelay: Long = 1000, // 1 second
            maxDelay: Long = 3000,     // 3 seconds
            factor: Double = 2.0,
            block: suspend () -> T,
        ): T {
            var currentDelay = initialDelay
            repeat(times - 1) {
                try {
                    return block()
                } catch (e: Exception) {
                    Timber.e(e, "Attempt failed, retrying in $currentDelay ms")
                }
                delay(currentDelay)
                currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
            }
            return block() // last attempt
        }
    }


}