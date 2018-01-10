package siarhei.luskanau.example.rxjava_bind_service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import io.reactivex.Single
import timber.log.Timber
import kotlin.properties.Delegates

class BackgroundService : Service() {

    var repository: BackgroundRepository by Delegates.notNull()

    override fun onCreate() {
        super.onCreate()
        Timber.d("BackgroundService: onCreate()")

        repository = BackgroundRepository()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("BackgroundService: onDestroy()\n--------")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return Service.START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Timber.d("BackgroundService: onBind()")
        return ServiceBinder()
    }

    inner class ServiceBinder : Binder() {
        fun getRepository(): BackgroundRepository = repository
    }

    companion object {

        fun bindToBackgroundRepository(context: Context): Single<BackgroundRepository> = RxServiceBindingFactory
                .bind<BackgroundService.ServiceBinder>(
                        context,
                        Intent(context, BackgroundService::class.java),
                        Context.BIND_AUTO_CREATE
                )
                .map { serviceBinder -> serviceBinder.getRepository() }

    }
}