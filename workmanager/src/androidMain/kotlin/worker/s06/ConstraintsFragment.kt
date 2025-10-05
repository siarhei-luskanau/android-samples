package worker.s06

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

private const val SLEEP_MILLIS = 30 * 1000L

class ConstraintsFragment : BaseBeginCancelWorkFragment() {
    override fun onBeginButtonPressed() {
        val constraints =
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()

        WorkManager.getInstance(requireContext())
            .beginWith(
                OneTimeWorkRequestBuilder<ConstraintsWorker>()
                    .setConstraints(constraints)
                    .addTag(WorkManagerConstants.TAG_ALL)
                    .build()
            ).enqueue()
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance(requireContext())
            .cancelAllWorkByTag(ConstraintsWorker::class.java.name)
    }
}

class ConstraintsWorker(context: Context, workerParams: WorkerParameters) :
    BaseWorker(
        context,
        workerParams
    ) {
    override suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        delay(SLEEP_MILLIS)
        return Result.success()
    }
}
