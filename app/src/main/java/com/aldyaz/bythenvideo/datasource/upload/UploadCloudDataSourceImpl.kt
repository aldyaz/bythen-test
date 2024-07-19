package com.aldyaz.bythenvideo.datasource.upload

import com.aldyaz.bythenvideo.datasource.base.HttpSourceState
import com.aldyaz.bythenvideo.datasource.remote.apiCall
import com.aldyaz.bythenvideo.datasource.upload.model.ProgressCallback
import com.aldyaz.bythenvideo.datasource.upload.model.ProgressRequestBody
import com.aldyaz.bythenvideo.datasource.upload.model.UploadVideoDto
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

class UploadCloudDataSourceImpl @Inject constructor(
    private val service: UploadVideoService
) : UploadCloudDataSource {

    override suspend fun upload(
        file: File,
        progressCallback: ProgressCallback
    ): HttpSourceState<UploadVideoDto> {
        val payload = UploadVideoPayload()
        val progressReqBody = ProgressRequestBody(
            file = file,
            contentType = "multipart/form-data",
            progressCallback = progressCallback
        )
        val multipart = MultipartBody.Part.createFormData(
            name = "form-data",
            filename = file.name,
            body = progressReqBody
        )
        return apiCall {
            service.uploadVideo(multipart, payload)
        }
    }
}