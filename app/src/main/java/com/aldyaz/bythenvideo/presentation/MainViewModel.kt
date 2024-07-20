package com.aldyaz.bythenvideo.presentation

import androidx.lifecycle.viewModelScope
import com.aldyaz.bythenvideo.base.domain.ResultState
import com.aldyaz.bythenvideo.base.presentation.BaseViewModel
import com.aldyaz.bythenvideo.datasource.upload.model.ProgressCallback
import com.aldyaz.bythenvideo.domain.model.NetworkStateDomainModel
import com.aldyaz.bythenvideo.domain.model.UploadVideoParam
import com.aldyaz.bythenvideo.domain.usecase.NetworkStatusUseCase
import com.aldyaz.bythenvideo.domain.usecase.UploadVideoUseCase
import com.aldyaz.bythenvideo.presentation.model.UploadVideoIntent
import com.aldyaz.bythenvideo.presentation.model.UploadVideoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val uploadVideoUseCase: UploadVideoUseCase,
    networkStatusUseCase: NetworkStatusUseCase
) : BaseViewModel<UploadVideoIntent>() {

    private val _uiState = MutableStateFlow(UploadVideoState.Initial)
    val uiState: StateFlow<UploadVideoState>
        get() = _uiState.asStateFlow()

    val isConnected = networkStatusUseCase(Unit).map {
        it is ResultState.Success && it.data == NetworkStateDomainModel.Connected
    }

    override fun onIntentReceived(intent: UploadVideoIntent) {
        when (intent) {
            is UploadVideoIntent.Submit -> uploadVideo(intent.file)
        }
    }

    private fun uploadVideo(file: File) = viewModelScope.launch {
        _uiState.update {
            it.copy(
                error = false
            )
        }
        val result = uploadVideoUseCase(
            UploadVideoParam(
                file = file,
                progressCallback = object : ProgressCallback {
                    override fun onProgressUpdate(progress: Long) {
                        _uiState.update {
                            it.copy(
                                progressValue = progress.toInt()
                            )
                        }
                    }
                }
            )
        )
        when (result) {
            is ResultState.Success -> {
                _uiState.update {
                    it.copy(
                        progressValue = 100,
                        uploadVideoPresentationModel = it.uploadVideoPresentationModel.copy(
                            signature = result.data.signature
                        )
                    )
                }
            }

            is ResultState.Error -> {
                _uiState.update {
                    it.copy(
                        progressValue = 100,
                        error = true
                    )
                }
            }
        }
    }

}