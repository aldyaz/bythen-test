package com.aldyaz.bythenvideo.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aldyaz.bythenvideo.R

@Composable
fun UploadProgress(
    value: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        val animateProgress by animateFloatAsState(
            targetValue = (value / 100).toFloat(),
            label = stringResource(R.string.label_progress_animation)
        )
        LinearProgressIndicator(
            progress = { animateProgress },
            modifier = Modifier.fillMaxWidth(0.6f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(R.string.label_uploading))
    }
}

@Preview
@Composable
fun UploadProgressPreview() {
    UploadProgress(
        value = 80,
        modifier = Modifier.background(Color.White)
    )
}
