package com.aldyaz.bythenvideo.presentation.model

data class UploadVideoState(
    val progressValue: Int? = null,
    val error: Boolean = false,
    val uploadVideoPresentationModel: UploadVideoPresentationModel = UploadVideoPresentationModel()
) {

    val uploadEligible = progressValue == null || progressValue == 100

    companion object {

        val Initial = UploadVideoState()
    }

}