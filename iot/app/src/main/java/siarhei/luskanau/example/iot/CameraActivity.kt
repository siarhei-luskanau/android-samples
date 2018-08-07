package siarhei.luskanau.example.iot

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import timber.log.Timber

abstract class CameraActivity : AppCompatActivity() {

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Starting CameraActivity")
    }

    override fun onResume() {
        super.onResume()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            cameraInfo()
        } else {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        Timber.d("requestPermissions")
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PERMISSIONS_REQUEST)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) =
            when (requestCode) {
                PERMISSIONS_REQUEST -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onPermissionsGranted()
                } else {
                    onPermissionsNotGranted()
                }
                else -> {
                }
            }


    private fun onPermissionsGranted() {
        Timber.d("onPermissionsGranted")
        cameraInfo()
    }

    private fun onPermissionsNotGranted() {
        Timber.d("onPermissionsNotGranted")
        finish()
    }

    private fun cameraInfo() {
        try {
            (getSystemService(CAMERA_SERVICE) as CameraManager?)?.let { cameraManager ->
                val cameraIdList = cameraManager.cameraIdList
                Timber.i("CameraIdList: %s", gson.toJson(cameraIdList))

                for (cameraId in cameraIdList) {
                    Timber.d("Using camera id %s", cameraId)
                    try {
                        val characteristics = cameraManager.getCameraCharacteristics(cameraId)

                        (characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP))?.let { configs ->
                            val outputFormats = configs.outputFormats
                            Timber.d("OutputFormats: %s", gson.toJson(outputFormats))
                            for (format in outputFormats) {
                                Timber.d("Getting sizes for format: %s", format)
                                for (s in configs.getOutputSizes(format)) {
                                    Timber.d("	%s", s)
                                }
                            }
                            val effects = characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS)
                            Timber.d("effects: %s", gson.toJson(effects))
                        }
                    } catch (e: CameraAccessException) {
                        Timber.d("Cam access exception getting characteristics.")
                    }

                }

            }
        } catch (e: Exception) {
            Timber.d("Camera access exception getting IDs")
        }

    }

    companion object {
        private const val PERMISSIONS_REQUEST = 200
    }
}
