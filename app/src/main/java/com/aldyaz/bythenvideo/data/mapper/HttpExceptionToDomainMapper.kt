package com.aldyaz.bythenvideo.data.mapper

import com.aldyaz.bythenvideo.datasource.remote.HttpResponseException
import com.aldyaz.bythenvideo.domain.exception.CloudApiException
import com.aldyaz.bythenvideo.domain.exception.DomainException
import com.aldyaz.bythenvideo.domain.exception.UnknownException

class HttpExceptionToDomainMapper : (Exception) -> DomainException {

    override fun invoke(p1: Exception): DomainException {
        return when (p1) {
            is HttpResponseException -> CloudApiException(p1.message)
            else -> UnknownException()
        }
    }
}