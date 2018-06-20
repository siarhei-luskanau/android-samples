package siarhei.luskanau.example.room_rxjava

import android.app.Application
import androidx.room.Room
import siarhei.luskanau.example.room_rxjava.persistence.AppDatabase
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
