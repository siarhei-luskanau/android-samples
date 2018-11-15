package worker01

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

class OneTimeWorkFragment : BaseBeginCancelWorkFragment() {

    override fun onBeginButtonPressed() {
        WorkManager.getInstance()
                .beginWith(
                        OneTimeWorkRequestBuilder<OneTimeWork>()
                                .addTag(WorkManagerConstants.TAG_ALL)
                                .build()
                ).enqueue()
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance().cancelAllWorkByTag(OneTimeWork::class.java.name)
    }
}

class OneTimeWork(
    context: Context,
    workerParams: WorkerParameters
) : BaseWorker(
        context,
        workerParams
) {

    override fun doWorkDelegate(): Result {
        Thread.sleep(10 * 1000)
        return Result.SUCCESS
    }
}