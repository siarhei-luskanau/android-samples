package siarhei.luskanau.example.rxjavabindservice.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import siarhei.luskanau.example.rxjavabindservice.api.ApiRepository
import timber.log.Timber

class BackgroundService : Service(), ApiRepository {

    companion object {
        private const val START_COUNTDOWN_KEY = "START_COUNTDOWN_KEY"
        private const val COUNTDOWN_MAX_VALUE = 10L
    }

    val countdownPublishSubject: PublishSubject<Int> = PublishSubject.create()
    var countdownDisposable: Disposable? = null

    override fun onCreate() {
        super.onCreate()
        Timber.d("onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy()\n--------")
        countdownDisposable?.dispose()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Timber.d("onStartCommand()")
        if (intent?.hasExtra(START_COUNTDOWN_KEY) == true) {
            startCountdownBackground()
        }
        return Service.START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Timber.d("onBind()")
        return ServiceBinder()
    }

    inner class ServiceBinder : Binder() {
        fun getApiRepository(): ApiRepository = this@BackgroundService
    }

    private fun startCountdownBackground() {
        countdownDisposable?.dispose()
        countdownDisposable = Flowable
                .intervalRange(
                        0,
                        COUNTDOWN_MAX_VALUE + 1,
                        0,
                        TimeUnit.SECONDS.toMillis(1),
                        TimeUnit.MILLISECONDS
                )
                .observeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .subscribeBy(
                        onNext = {
                            val countdownValue: Int = (COUNTDOWN_MAX_VALUE - it).toInt()
                            Timber.d("Runnable:countDown:$countdownValue")
                            countdownPublishSubject.onNext(countdownValue)
                        },
                        onError = { Timber.e(it) },
                        onComplete = { stopSelf() }
                )
    }

    override fun startCountdown(): Completable = Completable.fromAction {
        startService(
                Intent(this, BackgroundService::class.java)
                        .putExtra(START_COUNTDOWN_KEY, true)
        )
    }

    override fun watchCountdown(): Observable<Int> =
            countdownPublishSubject
}
