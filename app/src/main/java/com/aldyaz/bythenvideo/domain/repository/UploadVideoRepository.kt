package com.aldyaz.bythenvideo.domain.repository

import com.aldyaz.bythenvideo.base.domain.ResultState
import com.aldyaz.bythenvideo.datasource.upload.model.ProgressCallback
import com.aldyaz.bythenvideo.domain.model.UploadVideoDomainModel
import com.aldyaz.bythenvideo.domain.model.UploadVideoParam
import java.io.File

interface UploadVideoRepository {

    suspend fun upload(param: UploadVideoParam): ResultState<UploadVideoDomainModel>

}