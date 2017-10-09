package siarhei.luskanau.example.iot;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.PeripheralManagerService;
import com.google.gson.Gson;

public class IotActivity extends CameraActivity {

    private static final String TAG = IotActivity.class.getSimpleName();
    private final Gson GSON = new Gson();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Starting IotActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        buildInfo();
        pioInfo();
    }

    private void buildInfo() {
        Log.i(TAG, "Build.DEVICE: " + GSON.toJson(Build.DEVICE));
        Log.i(TAG, "Build.BOARD: " + GSON.toJson(Build.BOARD));
        Log.i(TAG, "Build.HARDWARE: " + GSON.toJson(Build.HARDWARE));
        Log.i(TAG, "Build.MODEL: " + GSON.toJson(Build.MODEL));
        Log.i(TAG, "Build.PRODUCT: " + GSON.toJson(Build.PRODUCT));
        Log.i(TAG, "Build.DISPLAY: " + GSON.toJson(Build.DISPLAY));
        Log.i(TAG, "Build.ID: " + GSON.toJson(Build.ID));
    }

    private void pioInfo() {
        try {
            final PeripheralManagerService peripheralManagerService = new PeripheralManagerService();
            Log.i(TAG, "GpioList: " + GSON.toJson(peripheralManagerService.getGpioList()));
            Log.i(TAG, "I2cBusList: " + GSON.toJson(peripheralManagerService.getI2cBusList()));
            Log.i(TAG, "PwmList: " + GSON.toJson(peripheralManagerService.getPwmList()));
            Log.i(TAG, "SpiBusList: " + GSON.toJson(peripheralManagerService.getSpiBusList()));
            Log.i(TAG, "UartDeviceList: " + GSON.toJson(peripheralManagerService.getUartDeviceList()));
        } catch (final Throwable e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
