package worker.s04

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

private const val SLEEP_MILLIS = 10 * 1000L

class UniqueWorkFragment : BaseBeginCancelWorkFragment() {
    override fun onBeginButtonPressed() {
        WorkManager.getInstance(requireContext())
            .beginUniqueWork(
                WorkManagerConstants.UNIQUE_WORK_NAME,
                ExistingWorkPolicy.KEEP,
                OneTimeWorkRequestBuilder<UniqueWorker>()
                    .addTag(WorkManagerConstants.TAG_ALL)
                    .build()
            ).enqueue()
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance(requireContext()).cancelAllWorkByTag(UniqueWorker::class.java.name)
    }
}

class UniqueWorker(context: Context, workerParams: WorkerParameters) :
    BaseWorker(
        context,
        workerParams
    ) {
    override suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        delay(SLEEP_MILLIS)
        return Result.success()
    }
}
