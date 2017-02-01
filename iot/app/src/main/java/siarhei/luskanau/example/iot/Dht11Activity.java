package siarhei.luskanau.example.iot;

import android.os.Bundle;
import android.util.Log;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

public class Dht11Activity extends CameraActivity {

    private static final String TAG = Dht11Activity.class.getSimpleName();
    //private static final String DHT11_GPIO_PIN = "BCM4";
    private static final String DHT11_READ_GPIO_PIN = "BCM26";
    private static final String DHT11_WRITE_GPIO_PIN = "BCM19";
    private static final String GPIO_BUTTON = "BCM22";

    private Gpio dht11ReadGpio;
    private Gpio dht11WriteGpio;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Starting Dht11Activity");

        PeripheralManagerService service = new PeripheralManagerService();
        try {
            dht11ReadGpio = service.openGpio(DHT11_READ_GPIO_PIN);
            dht11ReadGpio.setEdgeTriggerType(Gpio.EDGE_BOTH);
            dht11ReadGpio.registerGpioCallback(new GpioCallback() {
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
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        try {
            dht11WriteGpio = service.openGpio(DHT11_WRITE_GPIO_PIN);
            dht11WriteGpio.setDirection(Gpio.DIRECTION_IN);
            dht11WriteGpio.setActiveType(Gpio.ACTIVE_HIGH);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        try {
            button = new Button(GPIO_BUTTON, Button.LogicState.PRESSED_WHEN_LOW);
            button.setOnButtonEventListener(new Button.OnButtonEventListener() {
                @Override
                public void onButtonEvent(Button button, boolean pressed) {
                    try {
                        if (pressed) {
                            Log.d(TAG, "Button: " + pressed);
                            if (dht11WriteGpio != null) {
                                dht11WriteGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
                                dht11WriteGpio.setValue(true);
                                Thread.sleep(25);
                                dht11WriteGpio.setValue(false);
                                Thread.sleep(2);

                                dht11WriteGpio.setDirection(Gpio.DIRECTION_IN);
                                dht11WriteGpio.setActiveType(Gpio.ACTIVE_HIGH);
                            } else {
                                Log.d(TAG, "dht11WriteGpio is null");
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
            });
        } catch (IOException e) {
            Log.e(TAG, "button driver error", e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        closeGpio(dht11ReadGpio);
        dht11ReadGpio = null;

        closeGpio(dht11WriteGpio);
        dht11WriteGpio = null;
    }

    private void closeGpio(Gpio gpio) {
        if (gpio != null) {
            try {
                gpio.close();
            } catch (IOException e) {
                Log.e(TAG, "Error on PeripheralIO API", e);
            }
        }
    }
}
