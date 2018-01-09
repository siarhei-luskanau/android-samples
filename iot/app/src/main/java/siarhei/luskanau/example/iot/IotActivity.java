package siarhei.luskanau.example.iot;

import android.os.Build;
import android.os.Bundle;

import com.google.android.things.pio.PeripheralManagerService;
import com.google.gson.Gson;

import timber.log.Timber;

public class IotActivity extends CameraActivity {

    private final Gson GSON = new Gson();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("Starting IotActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        buildInfo();
        pioInfo();
    }

    private void buildInfo() {
        Timber.i("Build.DEVICE: " + GSON.toJson(Build.DEVICE));
        Timber.i("Build.BOARD: " + GSON.toJson(Build.BOARD));
        Timber.i("Build.HARDWARE: " + GSON.toJson(Build.HARDWARE));
        Timber.i("Build.MODEL: " + GSON.toJson(Build.MODEL));
        Timber.i("Build.PRODUCT: " + GSON.toJson(Build.PRODUCT));
        Timber.i("Build.DISPLAY: " + GSON.toJson(Build.DISPLAY));
        Timber.i("Build.ID: " + GSON.toJson(Build.ID));
    }

    private void pioInfo() {
        try {
            final PeripheralManagerService peripheralManagerService = new PeripheralManagerService();
            Timber.i("GpioList: " + GSON.toJson(peripheralManagerService.getGpioList()));
            Timber.i("I2cBusList: " + GSON.toJson(peripheralManagerService.getI2cBusList()));
            Timber.i("PwmList: " + GSON.toJson(peripheralManagerService.getPwmList()));
            Timber.i("SpiBusList: " + GSON.toJson(peripheralManagerService.getSpiBusList()));
            Timber.i("UartDeviceList: " + GSON.toJson(peripheralManagerService.getUartDeviceList()));
        } catch (final Throwable e) {
            Timber.e(e);
        }
    }
}
