package siarhei.luskanau.example.camera.appcrop

import android.app.Application
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import timber.log.Timber
import timber.log.Timber.DebugTree

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        RxPaparazzo.register(this)
    }
}
