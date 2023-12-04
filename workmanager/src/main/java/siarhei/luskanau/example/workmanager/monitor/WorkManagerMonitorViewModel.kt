package siarhei.luskanau.example.workmanager.monitor

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkInfo
import androidx.work.WorkManager

class WorkManagerMonitorViewModel : ViewModel() {
    fun getWorkStatusListLiveData(context: Context): LiveData<List<WorkInfo>> =
        WorkManager.getInstance(context).getWorkInfosByTagLiveData(WorkManagerConstants.TAG_ALL)
}
