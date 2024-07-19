package com.aldyaz.bythenvideo.data.mapper

import com.aldyaz.bythenvideo.datasource.upload.model.UploadVideoDto
import com.aldyaz.bythenvideo.domain.model.UploadVideoDomainModel

class UploadVideoToDomainMapper : (UploadVideoDto?) -> UploadVideoDomainModel {

    override fun invoke(p1: UploadVideoDto?): UploadVideoDomainModel {
        return UploadVideoDomainModel(
            publicId = p1?.publicId.orEmpty(),
            assetId = p1?.assetId.orEmpty(),
            version = p1?.version ?: 0,
            signature = p1?.signature.orEmpty()
        )
    }
}