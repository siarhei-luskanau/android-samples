package siarhei.luskanau.example.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

abstract class BaseWorker(
    context: Context,
    private val workerParams: WorkerParameters,
) : CoroutineWorker(
    context,
    workerParams,
) {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun doWork(): Result {
        val outputDataBuilder = Data.Builder()
        outputDataBuilder.putAll(workerParams.inputData)
        outputDataBuilder.putString(
            "start_${this.javaClass.simpleName}",
            getTimestamp(),
        )
        if (workerParams.runAttemptCount > 0) {
            outputDataBuilder.putString(
                "runAttemptCount_${this.javaClass.simpleName}",
                workerParams.runAttemptCount.toString(),
            )
        }

        return try {
            val result = doWorkDelegate(outputDataBuilder)

            outputDataBuilder.putString(
                "finish_${this.javaClass.simpleName}",
                getTimestamp(),
            )

            when (result) {
                is Result.Success -> Result.success(outputDataBuilder.build())
                is Result.Failure -> Result.failure(outputDataBuilder.build())
                is Result.Retry -> Result.retry()
                else -> Result.failure(outputDataBuilder.build())
            }
        } catch (throwable: Throwable) {
            Timber.e(throwable)

            outputDataBuilder.putString(
                "finish_${this.javaClass.simpleName}",
                getTimestamp(),
            )
            outputDataBuilder.putString(
                "throwable_${this.javaClass.simpleName}",
                "${throwable.javaClass.simpleName}: ${throwable.message}",
            )

            Result.failure(
                try {
                    outputDataBuilder.build()
                } catch (t: Throwable) {
                    t.printStackTrace()
                    Data.Builder()
                        .putString(
                            "throwable_${this.javaClass.simpleName}",
                            "${throwable.javaClass.simpleName}: ${throwable.message}",
                        ).build()
                },
            )
        }
    }

    abstract suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result

    private fun getTimestamp() =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH).format(Date())
}
