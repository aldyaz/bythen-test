package com.aldyaz.bythenvideo.di.module

import com.aldyaz.bythenvideo.data.mapper.HttpExceptionToDomainMapper
import com.aldyaz.bythenvideo.data.mapper.UploadVideoToDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MapperModule {

    @Provides
    fun provideHttpExceptionToDomainMapper(): HttpExceptionToDomainMapper = HttpExceptionToDomainMapper()

    @Provides
    fun provideUploadVideoToDomainMapper(): UploadVideoToDomainMapper = UploadVideoToDomainMapper()

}