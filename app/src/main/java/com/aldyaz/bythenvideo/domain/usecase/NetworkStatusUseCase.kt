package com.aldyaz.bythenvideo.domain.usecase

import com.aldyaz.bythenvideo.base.domain.CoroutinesContextProvider
import com.aldyaz.bythenvideo.base.domain.FlowUseCase
import com.aldyaz.bythenvideo.base.domain.ResultState
import com.aldyaz.bythenvideo.domain.model.NetworkStateDomainModel
import com.aldyaz.bythenvideo.domain.repository.NetworkStateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class NetworkStatusUseCase(
    private val networkStateRepository: NetworkStateRepository,
    private val coroutinesContextProvider: CoroutinesContextProvider
) : FlowUseCase<Unit, NetworkStateDomainModel>() {

    override fun execute(param: Unit): Flow<ResultState<NetworkStateDomainModel>> {
        return networkStateRepository.checkNetworkState()
            .flowOn(coroutinesContextProvider.io)
    }
}