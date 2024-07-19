package com.aldyaz.bythenvideo.data.repository

import com.aldyaz.bythenvideo.base.domain.ResultState
import com.aldyaz.bythenvideo.datasource.connection.NetworkStateDataSource
import com.aldyaz.bythenvideo.datasource.connection.model.NetworkStateDataModel
import com.aldyaz.bythenvideo.domain.model.NetworkStateDomainModel
import com.aldyaz.bythenvideo.domain.repository.NetworkStateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NetworkStateRepositoryImpl @Inject constructor(
    private val networkStateDataSource: NetworkStateDataSource
) : NetworkStateRepository {

    override fun checkNetworkState(): Flow<ResultState<NetworkStateDomainModel>> {
        return networkStateDataSource.observeNetworkState().map { state ->
            val result = when (state) {
                is NetworkStateDataModel.Connected -> NetworkStateDomainModel.Connected
                is NetworkStateDataModel.Disconnected -> NetworkStateDomainModel.Disconnected
                is NetworkStateDataModel.Unavailable -> NetworkStateDomainModel.Unavailable
            }
            ResultState.Success(result)
        }
    }
}