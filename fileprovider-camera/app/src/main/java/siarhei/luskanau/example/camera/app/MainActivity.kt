package siarhei.luskanau.example.camera.app

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.example.camera.library.CameraUtils
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var imageUriTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var roundedImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageUriTextView = findViewById(R.id.imageUriTextView)
        imageView = findViewById(R.id.imageView)
        roundedImageView = findViewById(R.id.roundedImageView)

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
        uri?.let {
            imageView.setImageURI(uri)
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
            val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
                    .apply { isCircular = true }
            roundedImageView.setImageDrawable(roundedBitmapDrawable)
            roundedImageView.setImageDrawable(roundedBitmapDrawable)
        } ?: run {
            imageView.setImageResource(R.drawable.ic_android_24dp)
            roundedImageView.setImageResource(R.drawable.ic_android_24dp)
        }
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }
}
