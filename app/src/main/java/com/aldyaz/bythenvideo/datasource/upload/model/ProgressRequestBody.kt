package com.aldyaz.bythenvideo.datasource.upload.model

import android.os.Handler
import android.os.Looper
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream

data class ProgressRequestBody(
    val file: File,
    val contentType: String,
    val progressCallback: ProgressCallback
) : RequestBody() {

    override fun contentType(): MediaType = contentType.toMediaType()

    override fun writeTo(sink: BufferedSink) {
        val fileLength = file.length()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        var uploaded = 0L
        FileInputStream(file).use { fis ->
            var read: Int
            val handler = Handler(Looper.getMainLooper())
            while (fis.read(buffer).also { read = it } != 1) {
                handler.post {
                    progressCallback.onProgressUpdate(100 * uploaded / fileLength)
                }
                uploaded += read.toLong()
                sink.write(buffer, 0, read)
            }
        }
    }

    companion object {
        private const val DEFAULT_BUFFER_SIZE = 2048
    }
}