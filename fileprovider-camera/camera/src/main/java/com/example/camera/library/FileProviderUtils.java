package com.example.camera.library;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;

public final class FileProviderUtils {

    private static final String AUTHORITIES_SUFFIX = ".provider";
    private static final String FILE_PROVIDER_PATHS = "temp";

    public static Uri getFileProviderUri(final Context context, final String fileName) {
        return FileProvider.getUriForFile(context,
                context.getPackageName() + AUTHORITIES_SUFFIX,
                createFile(context, fileName));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void deleteFile(final Context context, final String fileName) {
        final File file = createFile(context, fileName);
        file.delete();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static File createFile(final Context context, final String fileName) {
        final File fileProviderDir = new File(context.getFilesDir(), FILE_PROVIDER_PATHS);
        if (!fileProviderDir.exists()) {
            fileProviderDir.mkdirs();
        }
        return new File(fileProviderDir, fileName);
    }
}
