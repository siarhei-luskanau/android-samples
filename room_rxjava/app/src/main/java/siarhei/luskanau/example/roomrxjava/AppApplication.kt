package siarhei.luskanau.example.roomrxjava

import android.app.Application
import androidx.room.Room
import siarhei.luskanau.example.roomrxjava.persistence.AppDatabase
import timber.log.Timber

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

    val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(
                this.applicationContext,
                AppDatabase::class.java,
                "${this.packageName}.db"
        ).build()
    }
}
