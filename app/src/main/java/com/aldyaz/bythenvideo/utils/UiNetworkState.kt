package com.aldyaz.bythenvideo.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun rememberIsInitialConnected(
    connectivityManager: ConnectivityManager
): State<Boolean> {
    val activeNetwork = connectivityManager.activeNetwork
    val isConnected = connectivityManager.getNetworkCapabilities(activeNetwork)
        ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    return remember { mutableStateOf(isConnected) }
}