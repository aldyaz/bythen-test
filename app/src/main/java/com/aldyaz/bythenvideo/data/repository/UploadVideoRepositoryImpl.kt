package com.aldyaz.bythenvideo.data.repository

import com.aldyaz.bythenvideo.base.domain.ResultState
import com.aldyaz.bythenvideo.data.mapper.HttpExceptionToDomainMapper
import com.aldyaz.bythenvideo.data.mapper.UploadVideoToDomainMapper
import com.aldyaz.bythenvideo.datasource.base.HttpSourceState
import com.aldyaz.bythenvideo.datasource.upload.UploadCloudDataSource
import com.aldyaz.bythenvideo.domain.model.UploadVideoDomainModel
import com.aldyaz.bythenvideo.domain.model.UploadVideoParam
import com.aldyaz.bythenvideo.domain.repository.UploadVideoRepository
import javax.inject.Inject

class UploadVideoRepositoryImpl @Inject constructor(
    private val cloudDataSource: UploadCloudDataSource,
    private val uploadVideoToDomainMapper: UploadVideoToDomainMapper,
    private val httpExceptionToDomainMapper: HttpExceptionToDomainMapper
) : UploadVideoRepository {

    override suspend fun upload(param: UploadVideoParam): ResultState<UploadVideoDomainModel> {
        return when (val result = cloudDataSource.upload(
            param.file,
            param.progressCallback
        )) {
            is HttpSourceState.Success -> ResultState.Success(
                uploadVideoToDomainMapper(result.data)
            )

            is HttpSourceState.Error -> {
                ResultState.Error(httpExceptionToDomainMapper(result.exception))
            }
        }
    }
}