package com.aldyaz.bythenvideo.domain.usecase

import com.aldyaz.bythenvideo.base.domain.CoroutinesContextProvider
import com.aldyaz.bythenvideo.base.domain.ResultState
import com.aldyaz.bythenvideo.base.domain.UseCase
import com.aldyaz.bythenvideo.domain.model.UploadVideoDomainModel
import com.aldyaz.bythenvideo.domain.repository.UploadVideoRepository
import kotlinx.coroutines.withContext
import java.io.File

class UploadVideoUseCase(
    private val repository: UploadVideoRepository,
    private val coroutinesContextProvider: CoroutinesContextProvider
) : UseCase<File, UploadVideoDomainModel>() {

    override suspend fun execute(param: File): ResultState<UploadVideoDomainModel> {
        return withContext(coroutinesContextProvider.io) {
            repository.upload(param)
        }
    }
}