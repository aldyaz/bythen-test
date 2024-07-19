package com.aldyaz.bythenvideo.presentation

import androidx.lifecycle.viewModelScope
import com.aldyaz.bythenvideo.base.domain.ResultState
import com.aldyaz.bythenvideo.base.presentation.BaseViewModel
import com.aldyaz.bythenvideo.domain.usecase.UploadVideoUseCase
import com.aldyaz.bythenvideo.presentation.model.UploadVideoIntent
import com.aldyaz.bythenvideo.presentation.model.UploadVideoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val uploadVideoUseCase: UploadVideoUseCase
) : BaseViewModel<UploadVideoIntent>() {

    private val _uiState = MutableStateFlow(UploadVideoState.Default)
    val uiState: StateFlow<UploadVideoState>
        get() = _uiState.asStateFlow()

    override fun onIntentReceived(intent: UploadVideoIntent) {
        when (intent) {
            is UploadVideoIntent.Submit -> {}
        }
    }

    private fun uploadVideo(file: File) = viewModelScope.launch {
        when (val result = uploadVideoUseCase(file)) {
            is ResultState.Success -> {}
            is ResultState.Error -> {}
        }
    }

}