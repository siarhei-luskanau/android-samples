package siarhei.luskanau.example.iot

import android.os.Build
import android.os.Bundle
import com.google.android.things.pio.PeripheralManager
import com.google.gson.Gson
import timber.log.Timber

class IotActivity : CameraActivity() {

    private val gson = Gson()

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
        Timber.d("Build.DEVICE: %s", gson.toJson(Build.DEVICE))
        Timber.d("Build.BOARD: %s", gson.toJson(Build.BOARD))
        Timber.d("Build.HARDWARE: %s", gson.toJson(Build.HARDWARE))
        Timber.d("Build.MODEL: %s", gson.toJson(Build.MODEL))
        Timber.d("Build.PRODUCT: %s", gson.toJson(Build.PRODUCT))
        Timber.d("Build.DISPLAY: %s", gson.toJson(Build.DISPLAY))
        Timber.d("Build.ID: %s", gson.toJson(Build.ID))
    }

    private fun pioInfo() =
            try {
                val peripheralManager = PeripheralManager.getInstance()
                Timber.d("GpioList: %s", gson.toJson(peripheralManager.gpioList))
                Timber.d("I2cBusList: %s", gson.toJson(peripheralManager.i2cBusList))
                Timber.d("PwmList: %s", gson.toJson(peripheralManager.pwmList))
                Timber.d("SpiBusList: %s", gson.toJson(peripheralManager.spiBusList))
                Timber.d("UartDeviceList: %s", gson.toJson(peripheralManager.uartDeviceList))
            } catch (e: Throwable) {
                Timber.e(e)
            }
}
