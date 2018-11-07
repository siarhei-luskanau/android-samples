package siarhei.luskanau.example.workmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import timber.log.Timber
import java.util.Date

class AppWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(
        context,
        workerParams
) {

    override fun doWork(): Result =
            try {

                outputData = Data.Builder()
                        .putString("test_data", Date().toString())
                        .build()

                Thread.sleep(30 * 1000)

                if (true) {
                    throw RuntimeException("TestException")
                }

                Result.SUCCESS
            } catch (throwable: Throwable) {
                Timber.e(throwable)

                outputData = Data.Builder()
                        .putString("throwable", throwable.toString())
                        .build()

                Result.FAILURE
            }
}