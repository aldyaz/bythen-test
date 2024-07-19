package com.aldyaz.bythenvideo.domain.repository

import com.aldyaz.bythenvideo.base.domain.ResultState
import com.aldyaz.bythenvideo.domain.model.NetworkStateDomainModel
import kotlinx.coroutines.flow.Flow

interface NetworkStateRepository {

    fun checkNetworkState(): Flow<ResultState<NetworkStateDomainModel>>

}