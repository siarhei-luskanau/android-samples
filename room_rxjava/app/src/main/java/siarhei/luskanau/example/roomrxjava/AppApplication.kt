package siarhei.luskanau.example.roomrxjava

import android.app.Application
import siarhei.luskanau.example.roomrxjava.persistence.AppDatabase
import timber.log.Timber

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

    val appDatabase: AppDatabase by lazy { AppDatabase.buildAppDatabase(applicationContext) }
}
