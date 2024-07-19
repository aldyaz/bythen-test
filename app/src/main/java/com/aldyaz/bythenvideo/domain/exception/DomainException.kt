package com.aldyaz.bythenvideo.domain.exception

abstract class DomainException(
    cause: Throwable? = null
) : Exception(cause)