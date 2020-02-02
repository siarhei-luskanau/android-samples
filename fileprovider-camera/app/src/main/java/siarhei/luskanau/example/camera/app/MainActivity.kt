package siarhei.luskanau.example.camera.app

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.example.camera.library.CameraUtils
import java.util.Locale
import siarhei.luskanau.example.camera.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(LayoutInflater.from(this))
            .also {
                binding = it
                setContentView(binding.root)
            }

        findViewById<View>(R.id.cameraButton).setOnClickListener {
            val takePictureIntent = CameraUtils.createCameraIntent(applicationContext)
            takePictureIntent.resolveActivity(packageManager)?.let {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }

        findViewById<View>(R.id.galleryButton).setOnClickListener {
            val takePictureIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            takePictureIntent.resolveActivity(packageManager)?.let {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }

        showImageUri(null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val uri = data?.data ?: CameraUtils.getCameraTempFileProviderUri(applicationContext)

            showImageUri(uri)
        } else {
            showImageUri(null)
        }
    }

    private fun showImageUri(uri: Uri?) {
        binding.imageUriTextView.text = String.format(Locale.ENGLISH, "Uri: %s", uri.toString())
        uri?.let {
            binding.imageView.setImageURI(uri)
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
            val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
                .apply { isCircular = true }
            binding.roundedImageView.setImageDrawable(roundedBitmapDrawable)
        } ?: run {
            binding.imageView.setImageResource(R.drawable.ic_android_24dp)
            binding.roundedImageView.setImageResource(R.drawable.ic_android_24dp)
        }
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }
}
