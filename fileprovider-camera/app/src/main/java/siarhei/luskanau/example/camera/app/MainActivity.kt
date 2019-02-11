package siarhei.luskanau.example.camera.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.camera.library.CameraUtils
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var imageUriTextView: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageUriTextView = findViewById(R.id.imageUriTextView)
        imageView = findViewById(R.id.imageView)

        findViewById<View>(R.id.cameraButton).setOnClickListener {
            val takePictureIntent = CameraUtils.createCameraIntent(applicationContext)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }

        findViewById<View>(R.id.galleryButton).setOnClickListener {
            val takePictureIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }

        showImageUri(null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val uri = if (data != null) {
                data.data
            } else {
                CameraUtils.getCameraTempFileProviderUri(applicationContext)
            }
            showImageUri(uri)
        } else {
            showImageUri(null)
        }
    }

    private fun showImageUri(uri: Uri?) {
        imageUriTextView.text = String.format(Locale.ENGLISH, "Uri: %s", uri.toString())
        imageView.setImageURI(uri)
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }
}
