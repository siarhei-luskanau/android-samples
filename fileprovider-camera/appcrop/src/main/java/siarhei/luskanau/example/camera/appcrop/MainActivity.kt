package siarhei.luskanau.example.camera.appcrop

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.yalantis.ucrop.UCrop
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.Locale
import siarhei.luskanau.example.camera.appcrop.databinding.ActivityMainBinding
import timber.log.Timber

private const val MAX_BITMAP_SIZE = 1024

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
            RxPaparazzo.single(this)
                .useInternalStorage()
                .crop(getCropOptions())
                .usingCamera()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { showImageUri(Uri.fromFile(it.data().file)) },
                    onError = { Timber.e(it) }
                )
        }

        findViewById<View>(R.id.galleryButton).setOnClickListener {
            RxPaparazzo.single(this)
                .useInternalStorage()
                .crop(getCropOptions())
                .usingGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { showImageUri(Uri.fromFile(it.data().file)) },
                    onError = { Timber.e(it) }
                )
        }

        showImageUri(null)
    }

    private fun getCropOptions() = UCrop.Options().apply {
        setToolbarColor(ContextCompat.getColor(applicationContext, android.R.color.white))
        setStatusBarColor(ContextCompat.getColor(applicationContext, android.R.color.white))
        setToolbarWidgetColor(ContextCompat.getColor(applicationContext, android.R.color.black))
        setToolbarTitle("")
        withAspectRatio(1f, 1f)
        setShowCropGrid(false)
        setCompressionFormat(Bitmap.CompressFormat.PNG)
        setMaxBitmapSize(MAX_BITMAP_SIZE)
        setHideBottomControls(true)
    }

    private fun showImageUri(uri: Uri?) {
        binding.imageUriTextView.text = String.format(Locale.ENGLISH, "Uri: %s", uri.toString())
        uri?.let {
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
            val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
                .apply { isCircular = true }
            binding.imageView.setImageDrawable(roundedBitmapDrawable)
            binding.circularImageView.setImageURI(uri)
        } ?: run {
            binding.imageView.setImageResource(R.drawable.ic_android_24dp)
            binding.circularImageView.setImageURI(uri)
        }
    }
}
