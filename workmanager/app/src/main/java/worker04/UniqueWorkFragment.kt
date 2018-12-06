package worker04

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

class UniqueWorkFragment : BaseBeginCancelWorkFragment() {

    override fun onBeginButtonPressed() {
        WorkManager.getInstance()
                .beginUniqueWork(
                        WorkManagerConstants.UNIQUE_WORK_NAME,
                        ExistingWorkPolicy.KEEP,
                        OneTimeWorkRequestBuilder<UniqueWorker>()
                                .addTag(WorkManagerConstants.TAG_ALL)
                                .build()
                ).enqueue()
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance().cancelAllWorkByTag(UniqueWorker::class.java.name)
    }
}

class UniqueWorker(
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