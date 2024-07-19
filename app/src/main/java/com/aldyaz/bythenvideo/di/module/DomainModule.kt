package com.aldyaz.bythenvideo.di.module

import com.aldyaz.bythenvideo.base.domain.CoroutinesContextProvider
import com.aldyaz.bythenvideo.domain.repository.UploadVideoRepository
import com.aldyaz.bythenvideo.domain.usecase.UploadVideoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideCoroutinesContextProvider(): CoroutinesContextProvider {
        return CoroutinesContextProvider.Default
    }

    @Provides
    fun provideUploadVideoUseCase(
        repository: UploadVideoRepository,
        coroutinesContextProvider: CoroutinesContextProvider
    ): UploadVideoUseCase = UploadVideoUseCase(
        repository = repository,
        coroutinesContextProvider = coroutinesContextProvider
    )

}