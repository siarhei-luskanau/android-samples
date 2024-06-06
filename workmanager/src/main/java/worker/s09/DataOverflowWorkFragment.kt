package worker.s09

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import siarhei.luskanau.example.workmanager.BaseBeginCancelWorkFragment
import siarhei.luskanau.example.workmanager.BaseWorker
import siarhei.luskanau.example.workmanager.monitor.WorkManagerConstants

class DataOverflowWorkFragment : BaseBeginCancelWorkFragment() {
    override fun onBeginButtonPressed() {
        WorkManager.getInstance(requireContext())
            .beginWith(
                OneTimeWorkRequestBuilder<DataOverflowWork>()
                    .addTag(WorkManagerConstants.TAG_ALL)
                    .build()
            ).enqueue()
    }

    override fun onCancelButtonPressed() {
        WorkManager.getInstance(requireContext())
            .cancelAllWorkByTag(DataOverflowWork::class.java.name)
    }
}

class DataOverflowWork(context: Context, workerParams: WorkerParameters) :
    BaseWorker(
        context,
        workerParams
    ) {
    override suspend fun doWorkDelegate(outputDataBuilder: Data.Builder): Result {
        var i = 0
        while (true) {
            i++
            outputDataBuilder.putInt("key_$i", i)
            outputDataBuilder.build()
        }
    }
}
