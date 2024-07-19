package com.aldyaz.bythenvideo.datasource.connection

import com.aldyaz.bythenvideo.datasource.connection.model.NetworkStateDataModel
import kotlinx.coroutines.flow.Flow

interface NetworkStateDataSource {

    fun observeNetworkState(): Flow<NetworkStateDataModel>

}