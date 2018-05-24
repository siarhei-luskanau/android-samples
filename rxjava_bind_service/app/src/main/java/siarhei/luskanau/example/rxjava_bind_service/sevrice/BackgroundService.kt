package siarhei.luskanau.example.rxjava_bind_service.sevrice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import siarhei.luskanau.example.rxjava_bind_service.api.ApiRepository
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BackgroundService : Service(), ApiRepository {

    companion object {
        const val START_COUNTDOWN_KEY = "START_COUNTDOWN_KEY"
    }

    var runnable: Runnable? = null
    var countdownValue: Int = 0
    val countdownPublishSubject: PublishSubject<Int> = PublishSubject.create()

    override fun onCreate() {
        super.onCreate()
        Timber.d("onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy()\n--------")
        runnable = null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Timber.d("onStartCommand()")
        if (intent?.hasExtra(START_COUNTDOWN_KEY) == true) {
            startRunnable()
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

    private fun startRunnable() {
        countdownValue += 10
        Timber.d("Runnable:countDown:+10:$countdownValue")
        if (runnable == null) {
            runnable = object : Runnable {
                override fun run() {
                    while (true) {
                        try {
                            Timber.d("Runnable:countDown:$countdownValue")
                            countdownPublishSubject.onNext(countdownValue)

                            when {
                                this != runnable -> return
                                countdownValue <= 0 -> {
                                    runnable = null
                                    stopSelf()
                                    return
                                }
                                else -> {
                                    countdownValue--
                                    Thread.sleep(TimeUnit.SECONDS.toMillis(1))
                                }
                            }
                        } catch (e: Throwable) {
                        }
                    }
                }
            }
            Thread(runnable).start()
        }
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