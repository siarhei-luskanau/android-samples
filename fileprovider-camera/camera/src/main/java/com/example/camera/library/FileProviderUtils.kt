package com.example.camera.library

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

object FileProviderUtils {

    private const val AUTHORITIES_SUFFIX = ".provider"
    private const val FILE_PROVIDER_PATHS = "temp"

    fun getFileProviderUri(context: Context, fileName: String): Uri =
        FileProvider.getUriForFile(
            context,
            context.packageName + AUTHORITIES_SUFFIX,
            createFile(context, fileName),
        )

    fun deleteFile(context: Context, fileName: String) =
        createFile(context, fileName).delete()

    private fun createFile(context: Context, fileName: String): File {
        val fileProviderDir = File(context.filesDir, FILE_PROVIDER_PATHS)
        if (!fileProviderDir.exists()) {
            fileProviderDir.mkdirs()
        }
        return File(fileProviderDir, fileName)
    }
}
