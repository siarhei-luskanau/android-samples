package siarhei.luskanau.example.camera.app

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.example.camera.library.FileProviderUtils
import siarhei.luskanau.example.camera.app.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(LayoutInflater.from(this))
            .also {
                binding = it
                setContentView(binding.root)
            }

        binding.cameraButton.setOnClickListener {
            FileProviderUtils.deleteFile(this, CAMERA_TEMP_FILE_NAME)
            val uri = FileProviderUtils.getFileProviderUri(this, CAMERA_TEMP_FILE_NAME)
            registerForActivityResult(ActivityResultContracts.TakePicture()) {
                showImageUri(uri)
            }.launch(uri)
        }

        binding.galleryButton.setOnClickListener {
            registerForActivityResult(ActivityResultContracts.GetContent()) {
                showImageUri(it)
            }.launch("image/*")
        }

        showImageUri(null)
    }

    @Suppress("TooGenericExceptionCaught")
    private fun showImageUri(uri: Uri?) {
        binding.imageUriTextView.text = String.format(Locale.ENGLISH, "Uri: %s", uri.toString())
        try {
            uri?.let {
                val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
                val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
                    .apply { isCircular = true }
                binding.roundedImageView.setImageDrawable(roundedBitmapDrawable)
                binding.imageView.setImageURI(null)
                binding.imageView.setImageURI(uri)
            } ?: run {
                binding.imageView.setImageResource(R.drawable.ic_android_24dp)
                binding.roundedImageView.setImageResource(R.drawable.ic_android_24dp)
            }
        } catch (t: Throwable) {
            binding.imageUriTextView.text = t.toString()
        }
    }

    companion object {
        private const val CAMERA_TEMP_FILE_NAME = "camera_temp.jpg"
    }
}
