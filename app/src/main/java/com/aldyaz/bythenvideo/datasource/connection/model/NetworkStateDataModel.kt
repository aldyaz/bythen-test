package com.aldyaz.bythenvideo.datasource.connection.model

sealed class NetworkStateDataModel {

    data object Connected : NetworkStateDataModel()

    data object Disconnected : NetworkStateDataModel()

    data object Unavailable : NetworkStateDataModel()

}