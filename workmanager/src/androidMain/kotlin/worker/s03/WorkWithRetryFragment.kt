package worker.s03

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

private const val SLEEP_MILLIS = 10 * 1000L

class OneTimeWorkWithRetryFragment : BaseBeginCancelWorkFragment() {
    override fun onBeginButtonPressed() {
        WorkManager.getInstance(requireContext())
            .beginWith(
                OneTimeWorkRequestBuilder<OneTimeWorkWithRetry>()
                    .addTag(WorkManagerConstants.TAG_ALL)
                    .build()
            ).enqueue()
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance(requireContext())
            .cancelAllWorkByTag(OneTimeWorkWithRetry::class.java.name)
    }
}

class OneTimeWorkWithRetry(context: Context, workerParams: WorkerParameters) :
    BaseWorker(
        context,
        workerParams
    ) {
    override suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        delay(SLEEP_MILLIS)
        return Result.retry()
    }
}
