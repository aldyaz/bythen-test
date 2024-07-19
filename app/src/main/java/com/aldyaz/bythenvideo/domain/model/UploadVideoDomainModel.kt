package com.aldyaz.bythenvideo.domain.model

class UploadVideoDomainModel(
    val publicId: String,
    val assetId: String,
    val version: Int,
    val signature: String
)