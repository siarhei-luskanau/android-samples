package siarhei.luskanau.example.workmanager

import android.content.Context
import androidx.work.Data
import androidx.work.ListenableWorker.Result.FAILURE
import androidx.work.Worker
import androidx.work.WorkerParameters
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

abstract class BaseWorker(
    context: Context,
    private val workerParams: WorkerParameters
) : Worker(
        context,
        workerParams
) {

    override fun doWork(): Result =
            try {
                outputData = Data.Builder()
                        .putAll(workerParams.inputData)
                        .build()

                putStringToOutputData("runAttemptCount_${this.javaClass.simpleName}") { workerParams.runAttemptCount.toString() }
                putStringToOutputData("start_${this.javaClass.simpleName}") { getTimestamp() }
                val result = doWorkDelegate()
                putStringToOutputData("finish_${this.javaClass.simpleName}") { getTimestamp() }

                result
            } catch (throwable: Throwable) {
                Timber.e(throwable)

                putStringToOutputData("finish_${this.javaClass.simpleName}") { getTimestamp() }

                putStringToOutputData("throwable_${this.javaClass.simpleName}") {
                    "${throwable.javaClass.simpleName}: ${throwable.message}"
                }

                FAILURE
            }

    abstract fun doWorkDelegate(): Result

    private fun getTimestamp() =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH).format(Date())

    private fun putStringToOutputData(key: String, value: () -> String) {
        outputData = Data.Builder()
                .putAll(outputData)
                .putString(key, value.invoke())
                .build()
    }
}