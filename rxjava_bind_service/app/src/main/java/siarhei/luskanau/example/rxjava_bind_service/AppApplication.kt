package siarhei.luskanau.example.rxjava_bind_service

import android.app.Application
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        BackgroundService.bindToBackgroundRepository(this)
                .flatMapObservable { backgroundRepository -> backgroundRepository.getStrings() }
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .subscribe(
                        { onNextString: String -> Timber.d(onNextString) },
                        { t: Throwable? -> Timber.e(t) },
                        { Timber.d("onComplete") }
                )
    }
}
