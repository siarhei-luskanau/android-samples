package worker06

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

class ConstraintsFragment : BaseBeginCancelWorkFragment() {

    override fun onBeginButtonPressed() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()

        WorkManager.getInstance()
                .beginWith(
                        OneTimeWorkRequestBuilder<ConstraintsWorker>()
                                .setConstraints(constraints)
                                .addTag(WorkManagerConstants.TAG_ALL)
                                .build()
                ).enqueue()
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance().cancelAllWorkByTag(ConstraintsWorker::class.java.name)
    }
}

class ConstraintsWorker(
    context: Context,
    workerParams: WorkerParameters
) : BaseWorker(
        context,
        workerParams
) {

    override fun doWorkDelegate(): Result {
        Thread.sleep(30 * 1000)
        return Result.SUCCESS
    }
}