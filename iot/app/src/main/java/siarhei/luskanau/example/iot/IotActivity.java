package siarhei.luskanau.example.iot;

import android.os.Build;
import android.os.Bundle;

import com.google.android.things.pio.PeripheralManager;
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
        Timber.i("Build.DEVICE: %s", GSON.toJson(Build.DEVICE));
        Timber.i("Build.BOARD: %s", GSON.toJson(Build.BOARD));
        Timber.i("Build.HARDWARE: %s", GSON.toJson(Build.HARDWARE));
        Timber.i("Build.MODEL: %s", GSON.toJson(Build.MODEL));
        Timber.i("Build.PRODUCT: %s", GSON.toJson(Build.PRODUCT));
        Timber.i("Build.DISPLAY: %s", GSON.toJson(Build.DISPLAY));
        Timber.i("Build.ID: %s", GSON.toJson(Build.ID));
    }

    private void pioInfo() {
        try {
            final PeripheralManager peripheralManager = PeripheralManager.getInstance();
            Timber.i("GpioList: %s", GSON.toJson(peripheralManager.getGpioList()));
            Timber.i("I2cBusList: %s", GSON.toJson(peripheralManager.getI2cBusList()));
            Timber.i("PwmList: %s", GSON.toJson(peripheralManager.getPwmList()));
            Timber.i("SpiBusList: %s", GSON.toJson(peripheralManager.getSpiBusList()));
            Timber.i("UartDeviceList: %s", GSON.toJson(peripheralManager.getUartDeviceList()));
        } catch (final Throwable e) {
            Timber.e(e);
        }
    }
}
