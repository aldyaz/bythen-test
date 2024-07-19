package com.aldyaz.bythenvideo.domain.repository

import com.aldyaz.bythenvideo.base.domain.ResultState
import com.aldyaz.bythenvideo.domain.model.UploadVideoDomainModel
import java.io.File

interface UploadVideoRepository {

    suspend fun upload(file: File): ResultState<UploadVideoDomainModel>

}