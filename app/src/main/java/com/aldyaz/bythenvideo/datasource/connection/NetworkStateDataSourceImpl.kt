package com.aldyaz.bythenvideo.datasource.connection

import android.net.ConnectivityManager
import android.net.Network
import com.aldyaz.bythenvideo.datasource.connection.model.NetworkStateDataModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NetworkStateDataSourceImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : NetworkStateDataSource {

    override fun observeNetworkState(): Flow<NetworkStateDataModel> {
        return callbackFlow {
            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    trySend(NetworkStateDataModel.Connected)
                }

                override fun onUnavailable() {
                    trySend(NetworkStateDataModel.Unavailable)
                }

                override fun onLost(network: Network) {
                    trySend(NetworkStateDataModel.Disconnected)
                }
            }

            connectivityManager.registerDefaultNetworkCallback(networkCallback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }
    }
}