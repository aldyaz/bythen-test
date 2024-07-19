package com.aldyaz.bythenvideo.ui

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aldyaz.bythenvideo.R
import com.aldyaz.bythenvideo.presentation.MainViewModel
import com.aldyaz.bythenvideo.presentation.model.UploadVideoIntent
import com.aldyaz.bythenvideo.utils.createVideoFile
import com.aldyaz.bythenvideo.utils.getUri

@Composable
fun MainPage() {
    val context = LocalContext.current
    val viewModel: MainViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val videoFile by remember { mutableStateOf(context.createVideoFile()) }
    val uri by remember { mutableStateOf(videoFile.getUri(context)) }
    val captureVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo(),
        onResult = { isSuccessRecord ->
            viewModel.onIntentReceived(UploadVideoIntent.OnSuccessRecordVideo(isSuccessRecord))
            if (isSuccessRecord) {
                Toast.makeText(context, "Success record video!", Toast.LENGTH_SHORT).show()
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
        onClickUpload = {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        },
        fileName = if (uiState.uploadVideoPresentationModel.isSuccessRecordVideo) {
            videoFile.name
        } else null
    )
}

@Composable
fun MainScaffold(
    onClickUpload: () -> Unit,
    fileName: String?
) {
    Scaffold { contentPadding ->
        MainContent(
            onClickUpload = onClickUpload,
            fileName = fileName,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun MainContent(
    onClickUpload: () -> Unit,
    fileName: String? = null,
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
            onClickUpload = onClickUpload,
            fileName = fileName
        )
    }
}

@Composable
fun UploadPlaceholder(
    onClickUpload: () -> Unit,
    modifier: Modifier = Modifier,
    fileName: String? = null
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.Gray
        ),
        onClick = onClickUpload,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            if (fileName != null) {
                Text(
                    text = fileName,
                    color = Color.Gray
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.FileUpload,
                    contentDescription = stringResource(R.string.label_tag_file_upload),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.label_upload),
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScaffoldPreview() {
    MainScaffold(onClickUpload = { }, fileName = null)
}

@Preview
@Composable
fun UploadPlaceholderPreview() {
    UploadPlaceholder(onClickUpload = {})
}