package siarhei.luskanau.example.iot;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.PeripheralManagerService;
import com.google.gson.Gson;

public class IotActivity extends Dht11Activity {

    private static final String TAG = IotActivity.class.getSimpleName();
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        Log.i(TAG, "Build.DEVICE: " + gson.toJson(Build.DEVICE));
        Log.i(TAG, "Build.BOARD: " + gson.toJson(Build.BOARD));
        Log.i(TAG, "Build.HARDWARE: " + gson.toJson(Build.HARDWARE));
        Log.i(TAG, "Build.MODEL: " + gson.toJson(Build.MODEL));
        Log.i(TAG, "Build.PRODUCT: " + gson.toJson(Build.PRODUCT));
        Log.i(TAG, "Build.DISPLAY: " + gson.toJson(Build.DISPLAY));
        Log.i(TAG, "Build.ID: " + gson.toJson(Build.ID));
    }

    private void pioInfo() {
        try {
            PeripheralManagerService peripheralManagerService = new PeripheralManagerService();
            Log.i(TAG, "GpioList: " + gson.toJson(peripheralManagerService.getGpioList()));
            Log.i(TAG, "I2cBusList: " + gson.toJson(peripheralManagerService.getI2cBusList()));
            Log.i(TAG, "PwmList: " + gson.toJson(peripheralManagerService.getPwmList()));
            Log.i(TAG, "SpiBusList: " + gson.toJson(peripheralManagerService.getSpiBusList()));
            Log.i(TAG, "UartDeviceList: " + gson.toJson(peripheralManagerService.getUartDeviceList()));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
