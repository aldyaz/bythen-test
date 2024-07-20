@file:OptIn(ExperimentalMaterial3Api::class)

package com.aldyaz.bythenvideo.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aldyaz.bythenvideo.R

@Composable
fun ApiErrorBottomSheet(
    onDismiss: () -> Unit
) {
    InfoBottomSheetDialog(
        onDismiss = onDismiss,
        icon = Icons.Filled.Error
    ) {
        BottomSheetText(
            title = stringResource(R.string.label_no_internet_connection_title),
            description = stringResource(R.string.label_no_internet_connection_description)
        )
    }
}

@Composable
fun NetworkIssueBottomSheet(
    onDismiss: () -> Unit
) {
    InfoBottomSheetDialog(
        onDismiss = onDismiss
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
    icon: ImageVector = Icons.Filled.NetworkCheck,
    additionalText: @Composable () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    LaunchedEffect(Unit) {
        bottomSheetState.expand()
    }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState
    ) {
        BottomSheetLayout(
            icon = icon,
            additionalText = additionalText
        )
    }
}

@Composable
private fun BottomSheetLayout(
    icon: ImageVector = Icons.Filled.NetworkCheck,
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
    Text(text = title)
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = description)
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
