package siarhei.luskanau.example.workmanager

import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

class AppViewModel : ViewModel() {

    fun beginUniqueWork() {
        WorkManager.getInstance()
                .beginUniqueWork(
                        WorkManagerConstants.TAG_OUTPUT,
                        ExistingWorkPolicy.KEEP,
                        OneTimeWorkRequest.Builder(AppWorker::class.java)
                                .addTag(WorkManagerConstants.TAG_ALL)
                                .build()
                ).enqueue()
    }

    fun cancelUniqueWork() =
            WorkManager.getInstance().cancelUniqueWork(WorkManagerConstants.TAG_OUTPUT)
}