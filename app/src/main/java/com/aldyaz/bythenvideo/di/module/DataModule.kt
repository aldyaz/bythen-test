package com.aldyaz.bythenvideo.di.module

import com.aldyaz.bythenvideo.datasource.connection.NetworkStateDataSource
import com.aldyaz.bythenvideo.datasource.connection.NetworkStateDataSourceImpl
import com.aldyaz.bythenvideo.datasource.upload.UploadCloudDataSource
import com.aldyaz.bythenvideo.datasource.upload.UploadCloudDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindUploadCloudDataSource(
        impl: UploadCloudDataSourceImpl
    ): UploadCloudDataSource

    @Binds
    abstract fun bindNetworkStateDataSource(
        impl: NetworkStateDataSourceImpl
    ): NetworkStateDataSource

}