package com.aldyaz.bythenvideo.datasource.remote

import com.google.gson.annotations.SerializedName

data class ErrorBaseResponse(
    @SerializedName("error")
    val error: ErrorResponse? = null
)

data class ErrorResponse(
    @SerializedName("message")
    val message: String? = null
)