package com.aldyaz.bythenvideo.domain.exception

class UnknownException(
    override val message: String = "Unknown exception!"
) : DomainException()