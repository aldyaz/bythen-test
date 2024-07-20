package com.aldyaz.bythenvideo.ui

import android.Manifest
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aldyaz.bythenvideo.R
import com.aldyaz.bythenvideo.di.entrypoint.MainPageEntryPoint
import com.aldyaz.bythenvideo.presentation.MainViewModel
import com.aldyaz.bythenvideo.presentation.model.UploadVideoIntent
import com.aldyaz.bythenvideo.presentation.model.UploadVideoState
import com.aldyaz.bythenvideo.ui.component.ApiErrorBottomSheet
import com.aldyaz.bythenvideo.ui.component.NetworkIssueBottomSheet
import com.aldyaz.bythenvideo.ui.component.UploadPlaceholder
import com.aldyaz.bythenvideo.utils.createVideoFile
import com.aldyaz.bythenvideo.utils.getUri
import com.aldyaz.bythenvideo.utils.rememberIsInitialConnected
import dagger.hilt.android.EntryPointAccessors

@Composable
fun MainPage() {
    val context = LocalContext.current
    val viewModel: MainViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val entryPoint = remember {
        EntryPointAccessors.fromActivity(
            context as Activity,
            MainPageEntryPoint::class.java
        )
    }
    val initialConnected by rememberIsInitialConnected(entryPoint.connectivityManager())
    val isConnected by viewModel.isConnected.collectAsStateWithLifecycle(
        initialValue = initialConnected
    )
    val videoFile by remember { mutableStateOf(context.createVideoFile()) }
    val uri by remember { mutableStateOf(videoFile.getUri(context)) }
    val captureVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo(),
        onResult = { isSuccessRecord ->
            if (isSuccessRecord) {
                viewModel.onIntentReceived(UploadVideoIntent.Submit(videoFile))
            } else {
                Toast.makeText(context, "Failed record video!", Toast.LENGTH_SHORT).show()
            }
        }
    )
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                captureVideoLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Permission denied!", Toast.LENGTH_SHORT).show()
            }
        }
    )

    MainScaffold(
        uiState = uiState,
        isConnected = isConnected,
        onClickUpload = {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    )
}

@Composable
fun MainScaffold(
    uiState: UploadVideoState,
    isConnected: Boolean,
    onClickUpload: () -> Unit
) {
    Scaffold { contentPadding ->
        MainContent(
            uiState = uiState,
            isConnected = isConnected,
            onClickUpload = onClickUpload,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun MainContent(
    uiState: UploadVideoState,
    isConnected: Boolean,
    onClickUpload: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .then(modifier)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = stringResource(R.string.label_click_to_upload_video))
        Spacer(modifier = Modifier.height(16.dp))
        UploadPlaceholder(
            uploadEligible = uiState.uploadEligible,
            progressValue = uiState.progressValue,
            onClickUpload = {
                if (uiState.uploadEligible) {
                    onClickUpload()
                }
            }
        )

        if (!isConnected) {
            Toast.makeText(LocalContext.current, "Network DISCONNECTED!", Toast.LENGTH_SHORT).show()
            NetworkIssueBottomSheet(
                onDismiss = {},
                modifier = Modifier.navigationBarsPadding()
            )
        }

        if (uiState.error) {
            ApiErrorBottomSheet(
                onDismiss = {},
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}

@Preview
@Composable
fun MainScaffoldPreview() {
    MainScaffold(
        uiState = UploadVideoState.Initial.copy(
            progressValue = 10
        ),
        isConnected = true,
        onClickUpload = {}
    )
}