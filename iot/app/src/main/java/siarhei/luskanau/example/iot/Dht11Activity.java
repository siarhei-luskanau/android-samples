package siarhei.luskanau.example.iot;

import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

public class Dht11Activity extends CameraActivity {

    private static final String TAG = Dht11Activity.class.getSimpleName();
    //private static final String DHT11_GPIO_PIN = "BCM4";
    private static final String DHT11_GPIO_PIN = "BCM26";

    private Gpio dht11Gpio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Starting Dht11Activity");

        PeripheralManagerService service = new PeripheralManagerService();
        try {
            dht11Gpio = service.openGpio(DHT11_GPIO_PIN);

            dht11Gpio.registerGpioCallback(new GpioCallback() {
                @Override
                public boolean onGpioEdge(Gpio gpio) {
                    try {
                        Log.d(TAG, "Dht11: " + gpio.getValue());
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                    return true;
                }
            });

            dht11Gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            dht11Gpio.setValue(true);
            Thread.sleep(25);
            dht11Gpio.setValue(false);

            dht11Gpio.setDirection(Gpio.DIRECTION_IN);
            dht11Gpio.setEdgeTriggerType(Gpio.EDGE_BOTH);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (dht11Gpio != null) {
            Log.i(TAG, "Closing LED GPIO pin");
            try {
                dht11Gpio.close();
            } catch (IOException e) {
                Log.e(TAG, "Error on PeripheralIO API", e);
            } finally {
                dht11Gpio = null;
            }
        }
    }

}
