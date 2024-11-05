package com.example.data.common

/**
 * Handle and log exceptions
 */
suspend fun <T> sendRequest(action: suspend () -> T): Result<T> =
    try {
        Result.success(action())
    } catch (e: Exception) {
//        e.printStackTrace()
        Result.failure(e)
    }

fun <T> tryCatch(action: () -> T): T? =
    try {
        action()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }