package com.aldyaz.bythenvideo.di.module

import com.aldyaz.bythenvideo.data.repository.NetworkStateRepositoryImpl
import com.aldyaz.bythenvideo.data.repository.UploadVideoRepositoryImpl
import com.aldyaz.bythenvideo.domain.repository.NetworkStateRepository
import com.aldyaz.bythenvideo.domain.repository.UploadVideoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindUploadVideoRepository(
        impl: UploadVideoRepositoryImpl
    ): UploadVideoRepository

    @Binds
    abstract fun bindNetworkStateRepository(
        impl: NetworkStateRepositoryImpl
    ): NetworkStateRepository

}