package worker02

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

class OneTimeWorkWithErrorFragment : BaseBeginCancelWorkFragment() {

    override fun onBeginButtonPressed() {
        WorkManager.getInstance(requireContext())
            .beginWith(
                OneTimeWorkRequestBuilder<OneTimeWorkWithError>()
                    .addTag(WorkManagerConstants.TAG_ALL)
                    .build()
            ).enqueue()
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance(requireContext())
            .cancelAllWorkByTag(OneTimeWorkWithError::class.java.name)
    }
}

class OneTimeWorkWithError(
    context: Context,
    workerParams: WorkerParameters
) : BaseWorker(
    context,
    workerParams
) {

    override suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        Thread.sleep(20 * 1000)
        throw RuntimeException("TestException")
    }
}