package com.aldyaz.bythenvideo.data.mapper

import com.aldyaz.bythenvideo.datasource.upload.model.UploadVideoDto
import com.aldyaz.bythenvideo.domain.model.UploadVideoDomainModel

class UploadVideoToDomainMapper : (UploadVideoDto?) -> UploadVideoDomainModel {

    override fun invoke(p1: UploadVideoDto?): UploadVideoDomainModel {
        return UploadVideoDomainModel(
            url = p1?.url.orEmpty()
        )
    }
}