package com.aldyaz.bythenvideo.datasource.upload

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class UploadVideoPayload(
    @SerializedName("upload_preset")
    val uploadPreset: String = "",
    @SerializedName("public_id")
    val publicId: String = UUID.randomUUID().toString(),
    @SerializedName("api_key")
    val apiKey: String = ""
)