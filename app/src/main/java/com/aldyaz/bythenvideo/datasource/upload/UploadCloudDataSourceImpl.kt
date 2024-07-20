package com.aldyaz.bythenvideo.datasource.upload

import com.aldyaz.bythenvideo.datasource.base.HttpSourceState
import com.aldyaz.bythenvideo.datasource.remote.apiCall
import com.aldyaz.bythenvideo.datasource.upload.model.ProgressCallback
import com.aldyaz.bythenvideo.datasource.upload.model.ProgressRequestBody
import com.aldyaz.bythenvideo.datasource.upload.model.UploadVideoDto
import com.google.gson.Gson
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

class UploadCloudDataSourceImpl @Inject constructor(
    private val gson: Gson,
    private val service: UploadVideoService
) : UploadCloudDataSource {

    override suspend fun upload(
        file: File,
        progressCallback: ProgressCallback
    ): HttpSourceState<UploadVideoDto> {
        val payload = UploadVideoPayload()
        val progressReqBody = ProgressRequestBody(
            file = file,
            progressCallback = progressCallback
        )
        val filePart = MultipartBody.Part.createFormData(
            name = "file",
            filename = file.name,
            body = progressReqBody
        )
        val body = MultipartBody.Builder()
            .addFormDataPart(UPLOAD_PRESET, payload.uploadPreset)
            .addFormDataPart(PUBLIC_ID, payload.publicId)
            .addFormDataPart(API_KEY, payload.apiKey)
            .addPart(filePart)
            .build()

        return apiCall {
            service.uploadVideo(body)
        }
    }

    companion object {
        private const val UPLOAD_PRESET = "upload_preset"
        private const val PUBLIC_ID = "public_id"
        private const val API_KEY = "api_key"
    }
}