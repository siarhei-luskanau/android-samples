package siarhei.luskanau.example.workmanager

import androidx.work.Data
import androidx.work.Worker
import timber.log.Timber
import java.util.*

class AppWorker : Worker() {

    override fun doWork(): Result =
            try {

                outputData = Data.Builder()
                        .putString("test_data", Date().toString())
                        .build()

                Thread.sleep(30 * 1000)

                Result.SUCCESS
            } catch (t: Throwable) {
                Timber.e(t)

                Result.FAILURE
            }

}