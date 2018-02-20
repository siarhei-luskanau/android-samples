package siarhei.luskanau.example.rxjava_bind_service.sevrice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import siarhei.luskanau.example.rxjava_bind_service.api.ApiRepository
import siarhei.luskanau.example.rxjava_bind_service.api.BackgroundApiRepository
import timber.log.Timber
import kotlin.properties.Delegates

class BackgroundService : Service() {

    var apiRepository: ApiRepository by Delegates.notNull()

    override fun onCreate() {
        super.onCreate()
        Timber.d("BackgroundService: onCreate()")

        apiRepository = BackgroundApiRepository()
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
        fun getApiRepository(): ApiRepository = apiRepository
    }

}