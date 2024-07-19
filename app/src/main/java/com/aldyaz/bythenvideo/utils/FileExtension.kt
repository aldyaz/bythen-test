package com.aldyaz.bythenvideo.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Context.createVideoFile(): File {
    val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
    val timestamp = dateFormat.format(Date())
    val directory = getExternalFilesDir(Environment.DIRECTORY_MOVIES)
    return File.createTempFile(
        "VID_${timestamp}",
        ".mp4",
        directory
    )
}

fun File.getUri(context: Context): Uri {
    return FileProvider.getUriForFile(
        context,
        "${context.applicationContext.packageName}.fileprovider",
        this
    )
}