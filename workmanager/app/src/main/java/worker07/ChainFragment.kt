package worker07

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

class ChainFragment : BaseBeginCancelWorkFragment() {

    override fun onBeginButtonPressed() {

        WorkManager.getInstance()
                .beginWith(listOf(
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
                ))
                .then(OneTimeWorkRequestBuilder<ChainB1Worker>()
                        .addTag(WorkManagerConstants.TAG_ALL)
                        .addTag(TAG_CHAIN)
                        .build())
                .then(listOf(
                        OneTimeWorkRequestBuilder<ChainC1Worker>()
                                .addTag(WorkManagerConstants.TAG_ALL)
                                .addTag(TAG_CHAIN)
                                .build(),
                        OneTimeWorkRequestBuilder<ChainC2Worker>()
                                .addTag(WorkManagerConstants.TAG_ALL)
                                .addTag(TAG_CHAIN)
                                .build()
                ))
                .enqueue()
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance().cancelAllWorkByTag(TAG_CHAIN)
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

    override fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        Thread.sleep(5 * 1000)
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

    override fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        Thread.sleep(10 * 1000)
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

    override fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        Thread.sleep(5 * 1000)
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

    override fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        Thread.sleep(5 * 1000)
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

    override fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        Thread.sleep(10 * 1000)
        return Result.success()
    }
}
