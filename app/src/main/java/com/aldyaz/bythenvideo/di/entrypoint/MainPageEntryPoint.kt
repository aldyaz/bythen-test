package com.aldyaz.bythenvideo.di.entrypoint

import android.net.ConnectivityManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@EntryPoint
interface MainPageEntryPoint {

    fun connectivityManager(): ConnectivityManager

}