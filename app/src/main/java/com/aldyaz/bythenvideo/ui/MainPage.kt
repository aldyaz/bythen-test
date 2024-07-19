package com.aldyaz.bythenvideo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
fun MainPage() {
    Scaffold { contentPadding ->
        MainContent(
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .then(modifier)
    ) {
        Text(text = stringResource(R.string.label_click_to_upload_video))
        Spacer(modifier = Modifier.height(16.dp))
        UploadPlaceholder(onClick = {})
    }
}

@Composable
fun UploadPlaceholder(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .then(modifier)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentWidth()
                .align(Alignment.Center)
        ) {
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

@Preview
@Composable
fun MainPagePreview() {
    MainPage()
}

@Preview
@Composable
fun UploadPlaceholderPreview() {
    UploadPlaceholder(onClick = {})
}