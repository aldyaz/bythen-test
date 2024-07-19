package com.aldyaz.bythenvideo.domain.model

import com.aldyaz.bythenvideo.datasource.upload.model.ProgressCallback
import java.io.File

data class UploadVideoParam(
    val file: File,
    val progressCallback: ProgressCallback
)