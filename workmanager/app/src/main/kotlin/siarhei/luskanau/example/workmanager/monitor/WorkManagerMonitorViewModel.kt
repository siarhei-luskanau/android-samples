package siarhei.luskanau.example.workmanager.monitor

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.flow.Flow

class WorkManagerMonitorViewModel : ViewModel() {
    fun getWorkStatusListFlow(context: Context): Flow<List<WorkInfo>> =
        WorkManager.getInstance(context).getWorkInfosByTagFlow(WorkManagerConstants.TAG_ALL)
}
