package siarhei.luskanau.example.workmanager

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkStatus


class AppViewModel : ViewModel() {

    companion object {
        const val TAG_OUTPUT = "TAG_OUTPUT"
    }

    val workStatusListLiveData: LiveData<List<WorkStatus>>? by lazy {
        WorkManager.getInstance()?.getStatusesForUniqueWork(TAG_OUTPUT)
    }

    fun beginUniqueWork() {
        WorkManager.getInstance()
                ?.beginUniqueWork(
                        TAG_OUTPUT,
                        ExistingWorkPolicy.KEEP,
                        OneTimeWorkRequest.from(AppWorker::class.java)
                )
                ?.enqueue()

        WorkManager.getInstance()?.enqueue()
    }

    fun cancelUniqueWork() =
            WorkManager.getInstance()?.cancelUniqueWork(TAG_OUTPUT);

}