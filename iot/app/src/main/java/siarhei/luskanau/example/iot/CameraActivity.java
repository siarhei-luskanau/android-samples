package siarhei.luskanau.example.iot;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.util.Size;

import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import timber.log.Timber;

public abstract class CameraActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST = 200;
    private final Gson GSON = new Gson();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("Starting CameraActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPermissionsGranted()) {
            cameraInfo();
        } else {
            requestPermissions();
        }
    }

    public boolean isPermissionsGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions() {
        Timber.d("requestPermissions");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                PERMISSIONS_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onPermissionsGranted();
                } else {
                    onPermissionsNotGranted();
                }
                break;

            default:
        }
    }

    protected void onPermissionsGranted() {
        Timber.d("onPermissionsGranted");
        cameraInfo();
    }

    protected void onPermissionsNotGranted() {
        Timber.d("onPermissionsNotGranted");
        finish();
    }

    private void cameraInfo() {
        try {
            final CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            if (cameraManager != null) {
                final String[] cameraIdList = cameraManager.getCameraIdList();
                Timber.i("CameraIdList: %s", GSON.toJson(cameraIdList));

                for (final String cameraId : cameraIdList) {
                    Timber.d("Using camera id %s", cameraId);
                    try {
                        final CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);
                        final StreamConfigurationMap configs = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                        if (configs != null) {
                            final int[] outputFormats = configs.getOutputFormats();
                            Timber.i("OutputFormats: %s", GSON.toJson(outputFormats));
                            for (final int format : outputFormats) {
                                Timber.d("Getting sizes for format: %s", format);
                                for (final Size s : configs.getOutputSizes(format)) {
                                    Timber.d("	%s", s);
                                }
                            }
                            final int[] effects = characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS);
                            Timber.i("effects: %s", GSON.toJson(effects));
                        }
                    } catch (final CameraAccessException e) {
                        Timber.d("Cam access exception getting characteristics.");
                    }
                }
            }
        } catch (final Exception e) {
            Timber.d("Camera access exception getting IDs");
        }
    }
}
