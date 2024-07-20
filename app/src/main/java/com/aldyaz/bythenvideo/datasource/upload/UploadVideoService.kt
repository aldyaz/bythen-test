package com.aldyaz.bythenvideo.datasource.upload

import com.aldyaz.bythenvideo.datasource.upload.model.UploadVideoDto
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST

interface UploadVideoService {

    @POST("v1_1/dk3lhojel/video/upload")
    suspend fun uploadVideo(
        @Body payload: RequestBody
    ): Response<UploadVideoDto>

}