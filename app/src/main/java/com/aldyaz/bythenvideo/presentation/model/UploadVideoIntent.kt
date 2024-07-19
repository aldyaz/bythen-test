package com.aldyaz.bythenvideo.presentation.model

import java.io.File

sealed class UploadVideoIntent {

    data class OnSuccessRecordVideo(
        val isSuccess: Boolean
    ) : UploadVideoIntent()

    data class Submit(
        val file: File
    ) : UploadVideoIntent()

}