package com.aldyaz.bythenvideo.datasource.upload

import com.aldyaz.bythenvideo.datasource.base.HttpSourceState
import com.aldyaz.bythenvideo.datasource.upload.model.UploadVideoDto
import java.io.File

interface UploadVideoCloudDataSource {

    suspend fun upload(file: File): HttpSourceState<UploadVideoDto>

}