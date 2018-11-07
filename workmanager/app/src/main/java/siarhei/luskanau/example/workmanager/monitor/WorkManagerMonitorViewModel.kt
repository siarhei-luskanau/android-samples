package siarhei.luskanau.example.workmanager.monitor

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkManager
import androidx.work.WorkInfo

class WorkManagerMonitorViewModel : ViewModel() {

    val workStatusListLiveData: LiveData<List<WorkInfo>> by lazy {
        WorkManager.getInstance().getWorkInfosByTagLiveData(WorkManagerConstants.TAG_ALL)
    }
}