package worker08

import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants
import java.util.concurrent.TimeUnit

class PeriodicWorkFragment : BaseBeginCancelWorkFragment() {

    override fun onBeginButtonPressed() {
        WorkManager.getInstance()
                .enqueue(
                        PeriodicWorkRequestBuilder<PeriodicWork>(15, TimeUnit.MINUTES)
                                .addTag(WorkManagerConstants.TAG_ALL)
                                .build()
                )
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance().cancelAllWorkByTag(PeriodicWork::class.java.name)
    }
}

class PeriodicWork(
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