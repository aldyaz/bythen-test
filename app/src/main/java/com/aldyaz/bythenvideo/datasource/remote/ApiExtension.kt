package com.aldyaz.bythenvideo.datasource.remote

import com.aldyaz.bythenvideo.datasource.base.HttpSourceState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

suspend fun <T : Any> apiCall(
    method: suspend () -> Response<T>
): HttpSourceState<T> {
    var responseCode: Int? = null
    try {
        val response = method.invoke()
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            HttpSourceState.Success(body)
        } else {
            val errorBodyString = response.errorBody()?.string()
            val errorResponse = parseErrorResponse(errorBodyString)
            responseCode = response.code()
            HttpSourceState.Error(
                responseCode = responseCode,
                errorMessage = errorResponse?.error?.message
            )
        }
    } catch (err: Exception) {
        return HttpSourceState.Error(
            responseCode = responseCode,
            errorMessage = err.message
        )
    }
}

private fun parseErrorResponse(
    json: String?
): ErrorBaseResponse? = try {
    val gson = Gson()
    val errorResponse = gson.fromJson<ErrorBaseResponse>(
        json,
        object : TypeToken<ErrorBaseResponse>() {}.type
    )
    errorResponse
} catch (err: Exception) {
    null
}
