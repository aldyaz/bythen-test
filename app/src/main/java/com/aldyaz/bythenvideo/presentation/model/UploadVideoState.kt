package com.aldyaz.bythenvideo.presentation.model

data class UploadVideoState(
    val loading: Boolean = true,
    val errorMessage: String? = null,
    val uploadVideoPresentationModel: UploadVideoPresentationModel = UploadVideoPresentationModel()
) {

    companion object {

        val Default = UploadVideoState()
    }

}