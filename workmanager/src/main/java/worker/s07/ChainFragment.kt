package worker.s07

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

private const val SLEEP_MILLIS_5 = 5 * 1000L
private const val SLEEP_MILLIS_10 = 10 * 1000L

class ChainFragment : BaseBeginCancelWorkFragment() {

    override fun onBeginButtonPressed() {
        WorkManager.getInstance(requireContext())
            .beginWith(
                listOf(
                    OneTimeWorkRequestBuilder<ChainA1Worker>()
                        .setInputData(Data.Builder().putString("input_a1", "a1").build())
                        .addTag(WorkManagerConstants.TAG_ALL)
                        .addTag(TAG_CHAIN)
                        .build(),
                    OneTimeWorkRequestBuilder<ChainA2Worker>()
                        .setInputData(Data.Builder().putString("input_a2", "a2").build())
                        .addTag(WorkManagerConstants.TAG_ALL)
                        .addTag(TAG_CHAIN)
                        .build()
                )
            )
            .then(
                OneTimeWorkRequestBuilder<ChainB1Worker>()
                    .addTag(WorkManagerConstants.TAG_ALL)
                    .addTag(TAG_CHAIN)
                    .build()
            )
            .then(
                listOf(
                    OneTimeWorkRequestBuilder<ChainC1Worker>()
                        .addTag(WorkManagerConstants.TAG_ALL)
                        .addTag(TAG_CHAIN)
                        .build(),
                    OneTimeWorkRequestBuilder<ChainC2Worker>()
                        .addTag(WorkManagerConstants.TAG_ALL)
                        .addTag(TAG_CHAIN)
                        .build()
                )
            )
            .enqueue()
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance(requireContext()).cancelAllWorkByTag(TAG_CHAIN)
    }
}

private const val TAG_CHAIN = "TAG_CHAIN"

class ChainA1Worker(
    context: Context,
    workerParams: WorkerParameters
) : BaseWorker(
    context,
    workerParams
) {

    override suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        delay(SLEEP_MILLIS_5)
        return Result.success()
    }
}

class ChainA2Worker(
    context: Context,
    workerParams: WorkerParameters
) : BaseWorker(
    context,
    workerParams
) {

    override suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        delay(SLEEP_MILLIS_10)
        return Result.success()
    }
}

class ChainB1Worker(
    context: Context,
    workerParams: WorkerParameters
) : BaseWorker(
    context,
    workerParams
) {

    override suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        delay(SLEEP_MILLIS_5)
        return Result.success()
    }
}

class ChainC1Worker(
    context: Context,
    workerParams: WorkerParameters
) : BaseWorker(
    context,
    workerParams
) {

    override suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        delay(SLEEP_MILLIS_5)
        return Result.success()
    }
}

class ChainC2Worker(
    context: Context,
    workerParams: WorkerParameters
) : BaseWorker(
    context,
    workerParams
) {

    override suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        delay(SLEEP_MILLIS_10)
        return Result.success()
    }
}
