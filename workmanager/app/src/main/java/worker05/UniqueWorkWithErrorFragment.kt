package worker05

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

class UniqueWorkWithErrorFragment : BaseBeginCancelWorkFragment() {

    override fun onBeginButtonPressed() {
        WorkManager.getInstance()
                .beginUniqueWork(
                        WorkManagerConstants.UNIQUE_WORK_NAME,
                        ExistingWorkPolicy.APPEND,
                        OneTimeWorkRequestBuilder<UniqueWithErrorWorker>()
                                .addTag(WorkManagerConstants.TAG_ALL)
                                .build()
                ).enqueue()
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance().cancelAllWorkByTag(UniqueWithErrorWorker::class.java.name)
    }
}

class UniqueWithErrorWorker(
    context: Context,
    workerParams: WorkerParameters
) : BaseWorker(
        context,
        workerParams
) {

    override fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        Thread.sleep(20 * 1000)
        throw RuntimeException("TestException")
    }
}