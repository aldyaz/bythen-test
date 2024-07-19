package com.aldyaz.bythenvideo.datasource.upload

import com.aldyaz.bythenvideo.datasource.base.HttpSourceState
import com.aldyaz.bythenvideo.datasource.remote.apiCall
import com.aldyaz.bythenvideo.datasource.upload.model.UploadVideoDto
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UploadCloudDataSourceImpl @Inject constructor(
    private val service: UploadVideoService
) : UploadCloudDataSource {

    override suspend fun upload(file: File): HttpSourceState<UploadVideoDto> {
        val payload = UploadVideoPayload()
        val multipart = MultipartBody.Part.createFormData(
            name = "form-data",
            filename = file.name,
            body = file.asRequestBody()
        )
        return apiCall {
            service.uploadVideo(multipart, payload)
        }
    }
}