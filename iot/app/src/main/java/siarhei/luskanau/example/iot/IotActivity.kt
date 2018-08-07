package siarhei.luskanau.example.iot

import android.os.Build
import android.os.Bundle

import com.google.android.things.pio.PeripheralManager
import com.google.gson.Gson

import timber.log.Timber

class IotActivity : CameraActivity() {

    private val GSON = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Starting IotActivity")
    }

    override fun onResume() {
        super.onResume()
        buildInfo()
        pioInfo()
    }

    private fun buildInfo() {
        Timber.d("Build.DEVICE: %s", GSON.toJson(Build.DEVICE))
        Timber.d("Build.BOARD: %s", GSON.toJson(Build.BOARD))
        Timber.d("Build.HARDWARE: %s", GSON.toJson(Build.HARDWARE))
        Timber.d("Build.MODEL: %s", GSON.toJson(Build.MODEL))
        Timber.d("Build.PRODUCT: %s", GSON.toJson(Build.PRODUCT))
        Timber.d("Build.DISPLAY: %s", GSON.toJson(Build.DISPLAY))
        Timber.d("Build.ID: %s", GSON.toJson(Build.ID))
    }

    private fun pioInfo() =
            try {
                val peripheralManager = PeripheralManager.getInstance()
                Timber.d("GpioList: %s", GSON.toJson(peripheralManager.gpioList))
                Timber.d("I2cBusList: %s", GSON.toJson(peripheralManager.i2cBusList))
                Timber.d("PwmList: %s", GSON.toJson(peripheralManager.pwmList))
                Timber.d("SpiBusList: %s", GSON.toJson(peripheralManager.spiBusList))
                Timber.d("UartDeviceList: %s", GSON.toJson(peripheralManager.uartDeviceList))
            } catch (e: Throwable) {
                Timber.e(e)
            }

}
