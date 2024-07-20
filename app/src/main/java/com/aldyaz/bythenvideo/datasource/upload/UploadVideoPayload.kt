package com.aldyaz.bythenvideo.datasource.upload

import com.aldyaz.bythenvideo.BuildConfig
import java.util.UUID

data class UploadVideoPayload(
    val uploadPreset: String = BuildConfig.PRESET,
    val publicId: String = UUID.randomUUID().toString(),
    val apiKey: String = BuildConfig.API_KEY
)