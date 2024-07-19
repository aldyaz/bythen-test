package com.aldyaz.bythenvideo.datasource.remote

import java.io.IOException

class HttpResponseException(
    val httpCode: Int,
    override val message: String
) : IOException()