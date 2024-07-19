package com.aldyaz.bythenvideo.datasource.base

import com.aldyaz.bythenvideo.datasource.remote.HttpResponseException
import java.io.IOException

sealed class HttpSourceState<out T : Any> {

    data class Success<out T : Any>(
        val data: T?
    ) : HttpSourceState<T>()

    data class Error(
        private val responseCode: Int?,
        val errorMessage: String?
    ) : HttpSourceState<Nothing>() {

        val exception: Exception = when {
            responseCode !in 200..299 -> HttpResponseException(
                httpCode = responseCode ?: 0,
                message = errorMessage.orEmpty()
            )

            else -> IOException(errorMessage)
        }
    }

}