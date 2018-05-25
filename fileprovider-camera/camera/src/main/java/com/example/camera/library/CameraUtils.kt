package com.example.camera.library

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore

object CameraUtils {

    private const val CAMERA_TEMP_FILE_NAME = "camera_temp.jpg"

    fun createCameraIntent(context: Context): Intent {
        FileProviderUtils.deleteFile(context.applicationContext, CAMERA_TEMP_FILE_NAME)
        val uri = FileProviderUtils.getFileProviderUri(context.applicationContext, CAMERA_TEMP_FILE_NAME)

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

        val resolveInfoList = context.packageManager
                .queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY)
        if (resolveInfoList != null) {
            for (resolveInfo in resolveInfoList) {
                val packageName = resolveInfo.activityInfo.packageName
                context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
        }

        return takePictureIntent
    }

    fun getCameraTempFileProviderUri(context: Context): Uri =
            FileProviderUtils.getFileProviderUri(context.applicationContext, CAMERA_TEMP_FILE_NAME)

}
