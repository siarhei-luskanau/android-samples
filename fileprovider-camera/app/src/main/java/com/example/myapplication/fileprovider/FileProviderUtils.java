package com.example.myapplication.fileprovider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.List;

public final class FileProviderUtils {

    private static final String AUTHORITIES_SUFFIX = ".provider";
    private static final String FILE_PROVIDER_PATHS = "temp";
    private static final String CAMERA_TEMP_FILE_NAME = "camera_temp.jpg";

    public static Intent createCameraIntent(final Context context) {
        deleteCameraTempFile(context);
        final Uri uri = getCameraTempFileProviderUri(context.getApplicationContext());

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
        return FileProvider.getUriForFile(context,
                context.getPackageName() + AUTHORITIES_SUFFIX,
                createCameraTempFile(context));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static File createCameraTempFile(final Context context) {
        final File fileProviderDir = new File(context.getFilesDir(), FILE_PROVIDER_PATHS);
        if (!fileProviderDir.exists()) {
            fileProviderDir.mkdirs();
        }
        return new File(fileProviderDir, CAMERA_TEMP_FILE_NAME);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void deleteCameraTempFile(final Context context) {
        final File file = createCameraTempFile(context);
        file.delete();
    }
}
