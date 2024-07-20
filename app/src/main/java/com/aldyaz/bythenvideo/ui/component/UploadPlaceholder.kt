package com.aldyaz.bythenvideo.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aldyaz.bythenvideo.R

@Composable
fun UploadPlaceholder(
    uploadEligible: Boolean,
    progressValue: Int?,
    onClickUpload: () -> Unit,
    modifier: Modifier = Modifier
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
            .aspectRatio(4f)
            .then(modifier)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (uploadEligible) {
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
            } else {
                progressValue?.also {
                    UploadProgress(value = it)
                }
            }
        }
    }
}

@Preview
@Composable
fun UploadPlaceholderPreview() {
    UploadPlaceholder(
        uploadEligible = false,
        onClickUpload = {},
        progressValue = 10
    )
}
