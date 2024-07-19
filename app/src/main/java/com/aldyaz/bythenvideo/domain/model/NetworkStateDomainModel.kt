package com.aldyaz.bythenvideo.domain.model

sealed class NetworkStateDomainModel {

    data object Connected : NetworkStateDomainModel()

    data object Disconnected : NetworkStateDomainModel()

    data object Unavailable : NetworkStateDomainModel()

}