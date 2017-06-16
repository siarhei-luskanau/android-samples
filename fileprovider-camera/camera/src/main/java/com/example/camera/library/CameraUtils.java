package com.example.camera.library;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.List;

public final class CameraUtils {

    private static final String CAMERA_TEMP_FILE_NAME = "camera_temp.jpg";

    public static Intent createCameraIntent(final Context context) {
        FileProviderUtils.deleteFile(context.getApplicationContext(), CAMERA_TEMP_FILE_NAME);
        final Uri uri = FileProviderUtils.getFileProviderUri(context.getApplicationContext(), CAMERA_TEMP_FILE_NAME);

        final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        final List<ResolveInfo> resolveInfoList = context.getPackageManager()
                .queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (uri != null && resolveInfoList != null) {
            for (final ResolveInfo resolveInfo : resolveInfoList) {
                final String packageName = resolveInfo.activityInfo.packageName;
                context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        }

        return takePictureIntent;
    }

    public static Uri getCameraTempFileProviderUri(final Context context) {
        return FileProviderUtils.getFileProviderUri(context.getApplicationContext(), CAMERA_TEMP_FILE_NAME);
    }
}
