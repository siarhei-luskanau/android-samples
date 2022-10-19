package worker.s08

import android.content.Context
import androidx.work.Data
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants
import java.util.concurrent.TimeUnit

private const val SLEEP_MILLIS = 10 * 1000L

class PeriodicWorkFragment : BaseBeginCancelWorkFragment() {

    override fun onBeginButtonPressed() {
        WorkManager.getInstance(requireContext())
            .enqueue(
                PeriodicWorkRequestBuilder<PeriodicWork>(
                    repeatInterval = 15,
                    repeatIntervalTimeUnit = TimeUnit.MINUTES
                )
                    .addTag(WorkManagerConstants.TAG_ALL)
                    .build()
            )
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance(requireContext()).cancelAllWorkByTag(PeriodicWork::class.java.name)
    }
}

class PeriodicWork(
    context: Context,
    workerParams: WorkerParameters
) : BaseWorker(
    context,
    workerParams
) {

    override suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        delay(SLEEP_MILLIS)
        return Result.success()
    }
}
