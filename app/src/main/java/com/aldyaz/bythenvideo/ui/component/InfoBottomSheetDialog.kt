@file:OptIn(ExperimentalMaterial3Api::class)

package com.aldyaz.bythenvideo.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aldyaz.bythenvideo.R

@Composable
fun SuccessUploadBottomSheet(
    url: String,
    onClickUrl: (String) -> Unit,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {}
) {
    InfoBottomSheetDialog(
        onDismiss = onDismiss,
        icon = Icons.Filled.CheckCircle,
        iconTint = Color.Green,
        modifier = modifier
    ) {
        val annotatedString = buildAnnotatedString {
            if (url.isNotEmpty()) {
                pushStringAnnotation("url", url)
                withStyle(style = SpanStyle(LocalContentColor.current)) {
                    append(url)
                }
            } else {
                append("-")
            }
        }

        Text(text = stringResource(R.string.label_success_upload))
        Spacer(modifier = Modifier.height(8.dp))
        ClickableText(
            text = annotatedString,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClickUrl(url)
                }
        ) { offset ->
            val urlAnnotations = annotatedString.getStringAnnotations(
                tag = "url",
                start = offset,
                end = offset
            ).firstOrNull()
            urlAnnotations?.also {
                onClickUrl(it.item)
            }
        }
    }
}

@Composable
fun ApiErrorBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
) {
    InfoBottomSheetDialog(
        onDismiss = onDismiss,
        icon = Icons.Filled.Error,
        modifier = modifier
    ) {
        BottomSheetText(
            title = stringResource(R.string.label_error),
            description = stringResource(R.string.label_error_description)
        )
    }
}

@Composable
fun NetworkIssueBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
) {
    InfoBottomSheetDialog(
        onDismiss = onDismiss,
        modifier = modifier
    ) {
        BottomSheetText(
            title = stringResource(R.string.label_no_internet_connection_title),
            description = stringResource(R.string.label_no_internet_connection_description)
        )
    }
}

@Composable
fun InfoBottomSheetDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Filled.NetworkCheck,
    iconTint: Color = LocalContentColor.current,
    additionalText: @Composable () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    LaunchedEffect(Unit) {
        bottomSheetState.expand()
    }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        modifier = modifier
    ) {
        BottomSheetLayout(
            icon = icon,
            additionalText = additionalText,
            iconTint = iconTint
        )
    }
}

@Composable
private fun BottomSheetLayout(
    icon: ImageVector = Icons.Filled.NetworkCheck,
    iconTint: Color = LocalContentColor.current,
    additionalText: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "bottom-sheet-icon",
            tint = iconTint,
            modifier = Modifier.size(72.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        additionalText()
    }
}

@Composable
private fun BottomSheetText(
    title: String,
    description: String
) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = description,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun BottomSheetLayoutPreview() {
    BottomSheetLayout(
        icon = Icons.Filled.Error
    ) {
        BottomSheetText(
            title = stringResource(R.string.label_no_internet_connection_title),
            description = stringResource(R.string.label_no_internet_connection_description)
        )
    }
}
