package siarhei.luskanau.example.camera.appcrop

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.mikhaellopez.circularimageview.CircularImageView
import com.yalantis.ucrop.UCrop
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var imageUriTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var circularImageView: CircularImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageUriTextView = findViewById(R.id.imageUriTextView)
        imageView = findViewById(R.id.imageView)
        circularImageView = findViewById(R.id.circularImageView)

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
        setMaxBitmapSize(1024)
        setHideBottomControls(true)
    }

    private fun showImageUri(uri: Uri?) {
        imageUriTextView.text = String.format(Locale.ENGLISH, "Uri: %s", uri.toString())
        uri?.let {
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
            val roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
                    .apply { isCircular = true }
            imageView.setImageDrawable(roundedBitmapDrawable)
            circularImageView.setImageURI(uri)
        } ?: run {
            imageView.setImageResource(R.drawable.ic_android_24dp)
            circularImageView.setImageURI(uri)
        }
    }
}
