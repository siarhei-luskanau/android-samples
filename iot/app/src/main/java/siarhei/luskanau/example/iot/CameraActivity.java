package siarhei.luskanau.example.iot;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.Size;

import com.google.gson.Gson;

public class CameraActivity extends Activity {

    private static final String TAG = CameraActivity.class.getSimpleName();
    private static final int PERMISSIONS_REQUEST = 200;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Starting CameraActivity");
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
        Log.d(TAG, "requestPermissions");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
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
        Log.d(TAG, "onPermissionsGranted");
        cameraInfo();
    }

    protected void onPermissionsNotGranted() {
        Log.d(TAG, "onPermissionsNotGranted");
        finish();
    }

    private void cameraInfo() {
        try {
            CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            String[] cameraIdList = cameraManager.getCameraIdList();
            Log.i(TAG, "CameraIdList: " + gson.toJson(cameraIdList));

            for (String cameraId : cameraIdList) {
                Log.d(TAG, "Using camera id " + cameraId);
                try {
                    CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);
                    StreamConfigurationMap configs = characteristics.get(
                            CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                    for (int format : configs.getOutputFormats()) {
                        Log.d(TAG, "Getting sizes for format: " + format);
                        for (Size s : configs.getOutputSizes(format)) {
                            Log.d(TAG, "\t" + s.toString());
                        }
                    }
                    int[] effects = characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS);
                    for (int effect : effects) {
                        Log.d(TAG, "Effect available: " + effect);
                    }
                } catch (CameraAccessException e) {
                    Log.d(TAG, "Cam access exception getting characteristics.");
                }
            }
        } catch (CameraAccessException e) {
            Log.d(TAG, "Camera access exception getting IDs");
        }
    }
}
