package com.aldyaz.bythenvideo.presentation.model

data class UploadVideoState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val progressValue: Int? = null,
    val uploadVideoPresentationModel: UploadVideoPresentationModel = UploadVideoPresentationModel()
) {

    val uploadEligible = (progressValue == null || progressValue == 100) && !loading

    companion object {

        val Initial = UploadVideoState()
    }

}