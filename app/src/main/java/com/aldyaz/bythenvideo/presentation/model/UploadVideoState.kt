package com.aldyaz.bythenvideo.presentation.model

data class UploadVideoState(
    val progressValue: Int? = null,
    val errorMessage: String? = null,
    val uploadVideoPresentationModel: UploadVideoPresentationModel = UploadVideoPresentationModel(),
    val isNetworkConnected: Boolean = false
) {

    val clickEnabled = progressValue == null || progressValue == 100

    companion object {

        val Initial = UploadVideoState()
    }

}